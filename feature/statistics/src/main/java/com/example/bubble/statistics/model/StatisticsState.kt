package com.example.bubble.statistics.model

import com.example.bubble.domain.model.Statistic

sealed interface StatisticsState {
    data object DefaultState: StatisticsState
    data class EmptyDataState(val message: String): StatisticsState
    data class LoadedDataState(val data: Statistic): StatisticsState
    data class ErrorState(val message: String): StatisticsState
}