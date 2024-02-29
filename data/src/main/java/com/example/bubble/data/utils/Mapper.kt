package com.example.bubble.data.utils

import com.example.bubble.data.local.database.dbo.AwardEntity
import com.example.bubble.data.local.database.dbo.BubbleEntity
import com.example.bubble.data.local.database.dbo.HistoryEntity
import com.example.bubble.domain.model.Award
import com.example.bubble.domain.model.Bubble
import com.example.bubble.domain.model.History

fun AwardEntity.toAward(): Award {
    return Award(
        name = name,
        icon = icon,
        title = title,
        isOpen = isOpen
    )
}

fun Award.toAwardEntity(): AwardEntity {
    return AwardEntity(
        icon = icon,
        isOpen = isOpen,
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
        is DatabaseResource.Empty -> DatabaseResource.Empty(message = "Empty data")
        is DatabaseResource.Error -> DatabaseResource.Error(message = "Error")
        is DatabaseResource.LoadedData -> DatabaseResource.LoadedData(
            loadedData = data?.let(mapper)!!,
            message = message
        )
        is DatabaseResource.Loading -> DatabaseResource.Loading()
    }
}
