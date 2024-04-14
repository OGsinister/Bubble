package com.example.bubble.statistics.utls

import com.example.bubble.data.utils.DatabaseResource
import com.example.bubble.domain.model.Statistic
import com.example.bubble.statistics.model.StatisticsState

/*
internal fun DatabaseResource<Statistic>.toStatisticState(): StatisticsState {
    return when(this){
        is DatabaseResource.Default -> StatisticsState.DefaultState
        is DatabaseResource.Empty -> StatisticsState.EmptyDataState(message)
        is DatabaseResource.Error -> StatisticsState.ErrorState(message)
        is DatabaseResource.LoadedData -> StatisticsState.LoadedDataState(data = loadedData)
        is DatabaseResource.Loading -> StatisticsState.LoadingState
    }
}*/

internal fun DatabaseResource<List<Statistic>>.toStatisticState(): StatisticsState {
    return when(this){
        is DatabaseResource.Default -> StatisticsState.DefaultState
        is DatabaseResource.Empty -> StatisticsState.EmptyDataState(message = message)
        is DatabaseResource.Error -> StatisticsState.ErrorState(message)
        is DatabaseResource.LoadedData -> StatisticsState.LoadedDataState(data = loadedData)
        is DatabaseResource.Loading -> StatisticsState.LoadingState
    }
}