package com.example.bubble.water.model

import com.example.bubble.domain.model.Water

sealed class WaterState{
    data object DefaultState: WaterState()
    data object IsLoadingState: WaterState()
    data class EmptyDataState(val data: Water? = null, val message: String): WaterState()
    data class LoadedAwardsState(val data: Water): WaterState()
    data class ErrorState(val message: String? = null): WaterState()
}