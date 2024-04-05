package com.example.bubble.home.utils

import androidx.compose.ui.graphics.toArgb
import com.example.bubble.core.ui.utils.TagUI
import com.example.bubble.domain.model.Tag

fun Long.toTimeUIFormat(): String {
    val timerFormat =
        "${(this / 1_000 / 60).toString().padStart(2, '0')}:" +
        "${(this / 1_000 % 60).toString().padStart(2, '0')} "

    return timerFormat
}

fun TagUI.toTag(): Tag{
    return Tag(
        id = id,
        tagName = name!!,
        tagColor = color.toArgb(),
        tagIcon = icon
    )
}