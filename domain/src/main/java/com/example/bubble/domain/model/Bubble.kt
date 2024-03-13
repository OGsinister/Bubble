package com.example.bubble.domain.model

data class Bubble(
    val id: Int? = null,
    val tag: String? = null,
    val dateTime: String? = null
){
    companion object {
        const val BUBBLE_COUNT = 1
    }
}
