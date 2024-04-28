package com.example.bubble.relax

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bubble.core.utils.BubbleDispatchers
import com.example.bubble.relax.model.RelaxBubble
import com.example.bubble.relax.use_cases.GetBubblesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class RelaxViewModel @Inject constructor(
    private val getBubblesUseCase: GetBubblesUseCase,
    private val bubbleDispatchers: BubbleDispatchers,
): ViewModel() {

    /*internal val state: StateFlow<RelaxState> = getBubblesUseCase()
        .flowOn(bubbleDispatchers.main)
        .stateIn(viewModelScope, SharingStarted.Lazily, RelaxState.DefaultState())*/

    //private val _state = MutableStateFlow(mutableListOf(RelaxBubble(color = Color.Blue)))
    private val _state: MutableStateFlow<MutableList<RelaxBubble>> = MutableStateFlow(mutableListOf())
    val state: StateFlow<MutableList<RelaxBubble>> = _state.asStateFlow()

    private val colorList = listOf(Color.Blue, Color.Red, Color.Cyan, Color.Green, Color.Yellow)

    fun addBubble() {
        val newBubble = (0 until 9).map {
            RelaxBubble(
                color = colorList.random()
            )
        }

        _state.value.addAll(newBubble)
    }

    fun removeBubble(relaxBubble: RelaxBubble) {
        _state.value.remove(relaxBubble)
    }


    fun deleteBubble(id: Int) {
        // some bubbles storage using
        viewModelScope.launch(CoroutineName("updateBubbles")) {
            withContext(bubbleDispatchers.main){
                // delete by id
                // if bubble exist is storage -> delete
                // else -> null

                // sound effect activate

               // bubbleStorage.deleteBubble(id)
            }

            // if bubblesCount == 0 -> RelaxState.DefaultState()
        }
    }
}