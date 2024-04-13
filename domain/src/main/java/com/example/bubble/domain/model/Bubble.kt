package com.example.bubble.domain.model

import java.util.Date

data class Bubble(
    val id: Int? = null,
    val tag: Tag? = null,
    val focusTime: Long? = null,
    val date: String? = null,
    var startAnimation: Boolean? = false
){
    companion object {
        const val BUBBLE_COUNT = 1
    }
}

data class Tag(
    val id: Int = 0,
    val tagName: Int = 0,
    val tagColor: Int = 0,
    val tagIcon: Int = 0
)