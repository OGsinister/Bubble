package com.example.bubble.history.model

import com.example.bubble.domain.model.History

internal sealed interface HistoryState {
    data object DefaultState: HistoryState
    data object IsLoadingState: HistoryState
    data class EmptyDataState(val message: String): HistoryState
    data class LoadedDataState(val data: List<History>): HistoryState
    data class ErrorState(val message: String): HistoryState
}