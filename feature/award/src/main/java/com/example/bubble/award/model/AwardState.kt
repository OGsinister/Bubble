package com.example.bubble.award.model

internal sealed class AwardState{
    data object DefaultState: AwardState()
    data object IsLoadingState: AwardState()
    data class EmptyDataState(val data: List<Award>? = emptyList(), val message: String): AwardState()
    data class LoadedAwardsState(val data: List<Award>): AwardState()
    data class ErrorState(val message: String? = null): AwardState()
}