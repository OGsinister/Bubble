package com.example.bubble.home

import android.os.CountDownTimer
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bubble.core.utils.BubbleDispatchers
import com.example.bubble.core.ui.utils.NeedRefactoring
import com.example.bubble.data.local.sharedPref.WaterSharedPref
import com.example.bubble.data.repository.HistoryRepository
import com.example.bubble.data.utils.toHistoryEntity
import com.example.bubble.domain.model.Bubble
import com.example.bubble.domain.model.History
import com.example.bubble.home.model.Affirmation
import com.example.bubble.home.model.AffirmationResource
import com.example.bubble.home.model.BubbleTimer
import com.example.bubble.home.model.FocusResult
import com.example.bubble.home.model.HomeEvents
import com.example.bubble.home.model.HomeState
import com.example.bubble.home.model.SelectedTime
import com.example.bubble.core.ui.utils.TagUI
import com.example.bubble.domain.model.User
import com.example.bubble.home.utils.toTag
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@Suppress("SameParameterValue")
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val bubbleDispatchers: BubbleDispatchers,
    private val repository: HistoryRepository,
    private val sharedPref: WaterSharedPref
) : ViewModel() {

    private var _state = MutableStateFlow<HomeState>(HomeState.DefaultState)
    internal val state: StateFlow<HomeState> = _state.asStateFlow()

    private var _bubble = MutableStateFlow(Bubble())
    internal val bubble: StateFlow<Bubble> = _bubble.asStateFlow()

    private var _currentTime = MutableStateFlow(0L)
    internal val currentTime = _currentTime.asStateFlow()

    private var _affirmation = MutableStateFlow(Affirmation())
    internal val affirmation = _affirmation.asStateFlow()

    private var _bubbleTimer = MutableStateFlow(BubbleTimer())
    internal val bubbleTimer: StateFlow<BubbleTimer> = _bubbleTimer.asStateFlow()

    private var _tag = MutableStateFlow(TagUI())
    internal val tag: StateFlow<TagUI> = _tag.asStateFlow()

    private var _user = MutableStateFlow(User())
    internal val user: StateFlow<User> = _user.asStateFlow()

    internal var showTimeBottomSheet = mutableStateOf(false)
    internal var showTagBottomSheet = mutableStateOf(false)
    internal var dialogState = mutableStateOf(false)
    internal var showDialog = mutableStateOf(false)

    internal fun event(event: HomeEvents){
        when(event){
            HomeEvents.RunFocus -> {
                _state.value = HomeState.FocusRunning
                startTimer()
                createBubble()
            }

            is HomeEvents.StopFocus -> {
                stopTimer()
                viewModelScope.launch(bubbleDispatchers.io) {
                    when(event.result){
                        FocusResult.FAIL -> {
                            addToHistory(isDone = event.result.value)
                            showDialog(event.result.value)
                        }
                        FocusResult.SUCCESS -> {
                            updateWater(count = Bubble.BUBBLE_COUNT)
                            addToHistory(isDone = event.result.value)
                            showDialog(event.result.value)
                        }
                    }
                }
                _state.value = HomeState.DefaultState
            }

            is HomeEvents.SelectTag -> {
                _bubble.value = _bubble.value.copy(
                    tag = event.tag
                )
            }

            is HomeEvents.SelectTime -> {
                changeMillisInFuture(event.time)
            }
        }
    }

    @NeedRefactoring
    private fun createBubble() {
        _bubble.value = Bubble(
            id = 0,
            tag = _tag.value.toTag(),
            dateTime = "12"
        )
    }
    private fun showDialog(result: Boolean){
        dialogState.value = result
        showDialog.value = true
    }

    internal fun updateCurrentTag(currentTag: TagUI){
        _tag.value = currentTag
        _tag.value.currentTag = currentTag.currentTag
        Log.d("checkTag", currentTag.toString())
    }

    private fun startTimer(){
       viewModelScope.launch(bubbleDispatchers.main) {
           _bubbleTimer.value.apply {
               timer = object : CountDownTimer(millisInFuture, 1_000){
                   override fun onTick(millisUntilFinished: Long) {
                       _currentTime.value = millisUntilFinished
                       isActive = true

                       if(
                           ((currentTime.value / 1_000L).toInt() % 30) == 0
                           && _currentTime.value > 5_000L
                       ){
                            showAffirmation()
                       }
                   }

                   override fun onFinish() {
                       _currentTime.value = 0L
                       isActive = false

                       event(HomeEvents.StopFocus(result = FocusResult.SUCCESS))
                       addToHistory(isDone = true)
                   }
               }.start()
           }
       }
    }

    private fun stopTimer(){
        viewModelScope.launch(bubbleDispatchers.main) {
            _bubbleTimer.value.apply {
                timer?.cancel()
                timer = null
            }
        }
    }

    private fun showAffirmation(){
        viewModelScope.launch(bubbleDispatchers.main) {
            _affirmation.value = Affirmation(
                affirmationResource = AffirmationResource.entries
                    .toTypedArray()
                    .random(),
                isVisible = true
            )
            delay(5_000L)
            stopAffirmation()
        }
    }

    private fun stopAffirmation(){
        viewModelScope.launch(bubbleDispatchers.main) {
            _affirmation.value = Affirmation(
                affirmationResource = null,
                isVisible = false
            )
        }
    }

    private fun changeMillisInFuture(selectedTime: SelectedTime){
        viewModelScope.launch(bubbleDispatchers.main) {
            _bubbleTimer.value = BubbleTimer(
                millisInFuture = selectedTime.time
            )
        }
    }

    private fun updateWater(count: Int){
        sharedPref.updateBubbleCount(count)
    }

    private fun addToHistory(isDone: Boolean){
        val history = History(
            id = 0,
            isDone = isDone,
            bubble = _bubble.value
        )
        viewModelScope.launch(bubbleDispatchers.io) {
            repository.addBubbleToHistory(history.toHistoryEntity())
        }
    }

    // need sharedPreferences
    private fun changeUserName(newName: String){
        _user.value = _user.value.copy(
            name = newName
        )
    }
}