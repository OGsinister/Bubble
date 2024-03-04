package com.example.bubble.data.utils

import com.example.bubble.data.local.database.dbo.AwardEntity
import com.example.bubble.data.local.database.dbo.BubbleEntity
import com.example.bubble.domain.model.Award
import com.example.bubble.domain.model.Bubble

fun AwardEntity.toAward(): Award {
    return Award(
        name = name,
        icon = icon,
        title = title,
        isUnlocked = isUnlocked
    )
}

fun Award.toAwardEntity(): AwardEntity {
    return AwardEntity(
        icon = icon,
        isUnlocked = isUnlocked,
        name = name,
        title = title
    )
}

fun BubbleEntity.toBubble(): Bubble {
    return Bubble(
        volume = volume,
        dateTime = dateTime
    )
}

fun <I,O> DatabaseResource<I>.map(mapper: (I) -> O): DatabaseResource<O> {
    return when(this){
        is DatabaseResource.Default -> DatabaseResource.Default()
        is DatabaseResource.Empty -> DatabaseResource.Empty(message)
        is DatabaseResource.Error -> DatabaseResource.Error(message)
        is DatabaseResource.LoadedData -> DatabaseResource.LoadedData(
            loadedData = data?.let(mapper)!!,
            message = message
        )
        is DatabaseResource.Loading -> DatabaseResource.Loading()
    }
}
