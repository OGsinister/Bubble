package com.example.bubble.water

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bubble.data.utils.DatabaseResource
import com.example.bubble.domain.model.Water
import com.example.bubble.water.model.WaterState
import com.example.bubble.water.useCases.AddWaterUseCase
import com.example.bubble.water.useCases.GetWaterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class WaterViewModel @Inject constructor(
    private val getWaterUseCase: GetWaterUseCase,
    private val addWaterUseCase: AddWaterUseCase
): ViewModel(){

    internal val waterState: StateFlow<WaterState> = getWaterUseCase()
        .map {
            it.toWaterState()
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, WaterState.DefaultState)

    fun addWaterBubble(newBubble: Int){
        addWaterUseCase(newBubble)
    }
}

internal fun DatabaseResource<Water>.toWaterState(): WaterState {
    return when(this){
        is DatabaseResource.Default -> WaterState.DefaultState
        is DatabaseResource.Error -> WaterState.ErrorState(message)
        is DatabaseResource.LoadedData -> WaterState.LoadedAwardsState(loadedData)
        is DatabaseResource.Loading -> WaterState.IsLoadingState
        is DatabaseResource.Empty -> WaterState.EmptyDataState(message = message)
    }
}