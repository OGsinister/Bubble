package com.example.bubble.home

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bubble.core.utils.BubbleDispatchers
import com.example.bubble.core.utils.NeedRefactoring
import com.example.bubble.core.utils.NullPointerMayBe
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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
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

    private var _bubbleTimer = MutableStateFlow(BubbleTimer())
    private val bubbleTimer: StateFlow<BubbleTimer> = _bubbleTimer.asStateFlow()

    private var _currentTime = MutableStateFlow(0L)
    internal val currentTime = _currentTime.asStateFlow()

    private var _affirmation = MutableStateFlow(Affirmation())
    internal val affirmation = _affirmation.asStateFlow()

    internal fun event(event: HomeEvents){
        when(event){
            HomeEvents.RunFocus -> {
                _state.value = HomeState.FocusRunning
                startTimer()
                createBubble()
            }

            is HomeEvents.StopFocus -> {
                _state.value = HomeState.DefaultState
                stopTimer()
                viewModelScope.launch(bubbleDispatchers.io) {
                    when(event.result){
                        FocusResult.FAIL -> {
                            val history = createHistory(false)

                            // null pointer exception
                            repository.addBubbleToHistory(history.toHistoryEntity())
                        }
                        FocusResult.SUCCESS -> {
                            val history = createHistory(true)

                            @NullPointerMayBe
                            // null pointer exception
                            repository.addBubbleToHistory(history.toHistoryEntity())
                        }
                    }
                }

                updateWater(count = Bubble.BUBBLE_COUNT)
            }

            is HomeEvents.SelectTag -> {
                _bubble.value = _bubble.value.copy(
                    tag = event.tag
                )
            }
        }
    }

    @NeedRefactoring
    private fun createHistory(isDone: Boolean): History {
        return History(
            // need to refactor
            id = (0..111).random(),
            isDone = isDone,
            bubble = _bubble.value
        )
    }

    @NeedRefactoring
    private fun createBubble() {
        _bubble.value = Bubble(
            id = 1,
            tag = "Tag",
            dateTime = "12"
        )
    }

    private fun startTimer(){
       viewModelScope.launch(bubbleDispatchers.main) {
           _bubbleTimer.value.apply {
               timer = object : CountDownTimer(millisInFuture, 1_000){
                   override fun onTick(millisUntilFinished: Long) {
                       _currentTime.value = millisUntilFinished
                       isActive = true

                       if(currentTime.value % 6L == 0L && _currentTime.value > 5_000L){
                            showAffirmation()
                       }
                       //_currentTime.value = millisUntilFinished
                   }

                   override fun onFinish() {
                       _currentTime.value = 0L
                       isActive = false
                       //_currentTime.value = 0L
                       event(HomeEvents.StopFocus(result = FocusResult.SUCCESS))
                   }
               }.start()
           }
       }
    }

    private fun stopTimer(){
        viewModelScope.launch(bubbleDispatchers.main) {
            bubbleTimer.value.apply {
                timer?.cancel()
                timer = null
            }
        }
    }

    private fun showAffirmation(){
        viewModelScope.launch(bubbleDispatchers.main) {
            /*_affirmation.value = Affirmation(
                affirmationResource = AffirmationResource.random(),
                isVisible = true
            )*/
            _affirmation.value = Affirmation(
                affirmationResource = AffirmationResource.YOU_CAN_EVERYTHING,
                isVisible = true
            )
            delay(5000L)
            _affirmation.value = Affirmation(
                isVisible = false
            )
        }
    }

    private fun updateWater(count: Int){
        sharedPref.updateBubbleCount(count)
    }
}