package com.example.bubble.water.model

import com.example.bubble.domain.model.Water

sealed interface WaterState{
    data object DefaultState: WaterState
    data object IsLoadingState: WaterState
    data class EmptyDataState(val message: String): WaterState
    data class LoadedWaterState(val data: Water): WaterState
    data class ErrorState(val message: String): WaterState
}