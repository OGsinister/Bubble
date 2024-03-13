package com.example.bubble.water

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bubble.core.utils.BubbleDispatchers
import com.example.bubble.water.model.WaterState
import com.example.bubble.water.useCases.GetWaterUseCase
import com.example.bubble.water.utils.toWaterState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class WaterViewModel @Inject constructor(
    private val getWaterUseCase: GetWaterUseCase,
    private val bubbleDispatchers: BubbleDispatchers
): ViewModel(){

    internal val waterState: StateFlow<WaterState> = getWaterUseCase()
        .map {
            it.toWaterState()
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, WaterState.DefaultState)
}