package com.example.bubble.domain.model

data class Statistic(
    val countOfSession: Int? = null,
    val avgFocusTime: Long? = null,
    val weeklyFocusTime: Long? = null,
    val tagFocusData: List<FocusTag>? = null,
    val weeklyFocusMainData: List<WeeklyFocus>? = null,
    val successPercent: Float? = null,
    val allFocusCounts: Int? = null
)

data class FocusTag(
    val tag: List<Tag>? = null,
    val focusTime: Int? = null
)

data class WeeklyFocus(
    val totalTime: Long,
    val dayOfWeek: String
)