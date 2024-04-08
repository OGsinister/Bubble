package com.example.bubble.domain.model

data class Statistic(
    val countOfSession: Int? = null,
    val avgFocusTime: Long? = null,
    val weeklyFocusTime: Long? = null,
    val tagFocusData: FocusTag? = null,
    val weeklyFocusMainData: Long? = null,
    val successPercent: Int? = null
)

data class FocusTag(
    val tag: Tag? = null,
    val focusTime: Int? = null
)