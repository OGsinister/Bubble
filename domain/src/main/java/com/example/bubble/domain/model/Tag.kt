package com.example.bubble.domain.model

data class Tag(
    val name: String
)

enum class TagNames(val color: String){
    STUDY("Red"),
    WORK("Yellow"),
    READ("Blue"),
    HOME("Orange"),
    SELF_DEVELOP("Purple"),
}