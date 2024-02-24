package com.example.bubble.domain.model

data class Tag(
    val name: TagNames,
    val color: TagColors,
)

enum class TagNames{
    STUDY, WORK, SELF_DEVELOP, READ, HOME
}

enum class TagColors{
    RED, YELLOW, BLUE, ORANGE, PURPLE, GREEN, TURQUOISE, TOXIC_GREEN
}