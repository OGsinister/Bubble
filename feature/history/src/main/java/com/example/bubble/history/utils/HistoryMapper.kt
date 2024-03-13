package com.example.bubble.history.utils

import com.example.bubble.data.utils.DatabaseResource
import com.example.bubble.domain.model.History
import com.example.bubble.history.model.HistoryState

internal fun DatabaseResource<List<History>>.toHistoryState(): HistoryState {
    return when(this){
        is DatabaseResource.Default -> HistoryState.DefaultState
        is DatabaseResource.Empty -> HistoryState.EmptyDataState(message = message)
        is DatabaseResource.Error -> HistoryState.ErrorState(message)
        is DatabaseResource.LoadedData -> HistoryState.LoadedDataState(loadedData)
        is DatabaseResource.Loading -> HistoryState.IsLoadingState
    }
}