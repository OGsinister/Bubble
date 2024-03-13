package com.example.bubble.water.utils

import com.example.bubble.data.utils.DatabaseResource
import com.example.bubble.domain.model.Water
import com.example.bubble.water.model.WaterState

internal fun DatabaseResource<Water>.toWaterState(): WaterState {
    return when(this){
        is DatabaseResource.Default -> WaterState.DefaultState
        is DatabaseResource.Error -> WaterState.ErrorState(message)
        is DatabaseResource.LoadedData -> WaterState.LoadedDataState(loadedData)
        is DatabaseResource.Loading -> WaterState.IsLoadingState
        is DatabaseResource.Empty -> WaterState.EmptyDataState(message = message)
    }
}