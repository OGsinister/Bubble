package com.example.bubble.award.model

import com.example.bubble.domain.model.Award

internal sealed interface AwardState{
    data object DefaultState: AwardState
    data object IsLoadingState: AwardState
    data class EmptyDataState(val message: String): AwardState
    data class LoadedAwardsState(val data: List<Award>): AwardState
    data class ErrorState(val message: String): AwardState
}