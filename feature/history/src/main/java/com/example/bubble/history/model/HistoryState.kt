package com.example.bubble.history.model

import com.example.bubble.domain.model.History

internal sealed class HistoryState {
    data object DefaultState: HistoryState()
    data object IsLoadingState: HistoryState()
    data class EmptyDataState(val data: List<History>? = emptyList(), val message: String): HistoryState()
    data class LoadedAwardsState(val data: List<History>): HistoryState()
    data class ErrorState(val message: String? = null): HistoryState()
}