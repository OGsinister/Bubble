package com.example.bubble.domain.model

data class Bubble(
    val id: Int? = null,
    val tag: Tag? = null,
    val dateTime: Long? = null,
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