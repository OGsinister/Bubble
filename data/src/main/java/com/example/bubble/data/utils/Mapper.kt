package com.example.bubble.data.utils

import com.example.bubble.data.local.database.dbo.AwardEntity
import com.example.bubble.data.local.database.dbo.BubbleEntity
import com.example.bubble.data.local.database.dbo.FocusTagEntity
import com.example.bubble.data.local.database.dbo.HistoryEntity
import com.example.bubble.data.local.database.dbo.StatisticEntity
import com.example.bubble.data.local.database.dbo.TagEntity
import com.example.bubble.data.local.database.dbo.TaskEntity
import com.example.bubble.data.local.database.dbo.WeeklyFocusEntity
import com.example.bubble.domain.model.Award
import com.example.bubble.domain.model.Bubble
import com.example.bubble.domain.model.FocusTag
import com.example.bubble.domain.model.History
import com.example.bubble.domain.model.Statistic
import com.example.bubble.domain.model.Tag
import com.example.bubble.domain.model.Task
import com.example.bubble.domain.model.WeeklyFocus

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
fun AwardEntity.toUIAward(): Award {
    return Award(
        name = name,
        title = title,
        icon = icon,
        isUnlocked = isUnlocked
    )
}

fun BubbleEntity.toBubble(): Bubble {
    return Bubble(
        id = id,
        tag = tag.toTag(),
        focusTime = focusTime
    )
}

fun Bubble.toBubbleEntity(): BubbleEntity{
    return BubbleEntity(
        id = id!!,
        tag = tag?.toTagEntity()!!,
        focusTime = focusTime!!,
        date = date!!
    )
}

fun TaskEntity.toUITask(): Task {
    return Task(
        id = id,
        name = name,
        description = description,
        dateTime = dateTime,
        tag = tag
    )
}

fun Task.toTaskEntity(): TaskEntity {
    return TaskEntity(
        id = id,
        name = name,
        description = description,
        dateTime = dateTime,
        tag = tag
    )
}

fun HistoryEntity.toUIHistory(): History {
    return History(
        isDone = isDone,
        bubble = bubble.toBubble()
    )
}

fun History.toHistoryEntity(): HistoryEntity {
    return HistoryEntity(
        isDone = isDone,
        bubble = bubble.toBubbleEntity()
    )
}

fun StatisticEntity.toStatistic(): Statistic {
    return Statistic(
        countOfSession = countOfSession,
        avgFocusTime = avgFocusTime,
        tagFocusData = tagFocusData?.map { it.toFocusTag() },
        weeklyFocusTime = weeklyFocusTime,
        weeklyFocusMainData = weeklyFocusMainData.map { it.toWeeklyFocus() },
        successPercent = successPercent,
        allFocusCounts = allFocusCounts
    )
}

fun WeeklyFocusEntity.toWeeklyFocus(): WeeklyFocus {
    return WeeklyFocus(
        totalTime = totalTime!!,
        dayOfWeek = dayOfWeek!!
    )
}

fun WeeklyFocus.toWeeklyFocusEntity(): WeeklyFocusEntity {
    return WeeklyFocusEntity(
        totalTime = totalTime,
        dayOfWeek = dayOfWeek
    )
}

fun FocusTag.toFocusTagEntity(): FocusTagEntity{
    return FocusTagEntity(
        tag = tag?.map { it.toTagEntity() },
        focusTime = focusTime
    )
}

fun FocusTagEntity.toFocusTag(): FocusTag{
    return FocusTag(
        tag = tag?.map { it.toTag() },
        focusTime = focusTime
    )
}

fun Tag.toTagEntity(): TagEntity{
    return TagEntity(
        name = tagName,
        color = tagColor,
        icon = tagIcon,
        totalTime = totalTime
    )
}

fun TagEntity.toTag(): Tag{
    return Tag(
        tagName = name!!,
        tagColor = color,
        tagIcon = icon,
        totalTime = totalTime
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


