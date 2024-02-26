package com.example.bubble.data.utils

import com.example.bubble.data.dbo.AwardEntity
import com.example.bubble.data.dbo.BubbleEntity
import com.example.bubble.data.dbo.HistoryEntity
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

fun Award.toAwardEntity(): AwardEntity{
    return AwardEntity(
        icon = icon,
        isOpen = isOpen,
        name = name,
        title = title
    )
}

fun HistoryEntity.toHistory(): History{
    return History(
        isDone = isDone,
        bubble = bubble.toBubble()
    )
}

fun BubbleEntity.toBubble(): Bubble {
    return Bubble(
        volume = volume,
        dateTime = dateTime
    )
}
