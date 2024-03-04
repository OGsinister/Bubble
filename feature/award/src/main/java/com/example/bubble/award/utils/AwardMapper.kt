package com.example.bubble.award.utils

import com.example.bubble.award.model.AwardState
import com.example.bubble.data.local.database.dbo.AwardEntity
import com.example.bubble.data.utils.DatabaseResource
import com.example.bubble.domain.model.Award

internal fun AwardEntity.toUIAward(): Award {
    return Award(
        name = name,
        title = title,
        icon = icon,
        isUnlocked = isUnlocked
    )
}

internal fun DatabaseResource<List<Award>>.toAwardState(): AwardState {
    return when(this){
        is DatabaseResource.Default -> AwardState.DefaultState
        is DatabaseResource.Empty -> AwardState.EmptyDataState(message = message)
        is DatabaseResource.Error -> AwardState.ErrorState(message)
        is DatabaseResource.LoadedData -> AwardState.LoadedAwardsState(loadedData)
        is DatabaseResource.Loading -> AwardState.IsLoadingState
    }
}