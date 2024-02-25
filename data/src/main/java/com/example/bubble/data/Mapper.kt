package com.example.bubble.data

import com.example.bubble.data.model.AwardEntity
import com.example.bubble.data.model.BubbleEntity
import com.example.bubble.data.model.HistoryEntity
import com.example.bubble.data.model.TagEntity
import com.example.bubble.data.model.WaterEntity
import com.example.bubble.domain.model.Award
import com.example.bubble.domain.model.Bubble
import com.example.bubble.domain.model.History
import com.example.bubble.domain.model.Tag
import com.example.bubble.domain.model.Water

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
        id = 1,
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
        dateTime = dateTime,
        tag = tag.toTag()
    )
}

fun TagEntity.toTag(): Tag{
    return Tag(
        name = name
    )
}

fun Water.toWaterEntity(): WaterEntity{
    return WaterEntity(
        commonWater = commonWater,
        title = title,
        comparison = comparison
    )
}

fun WaterEntity.toWater(): Water{
    return Water(
        commonWater = commonWater,
        title = title,
        comparison = comparison
    )
}