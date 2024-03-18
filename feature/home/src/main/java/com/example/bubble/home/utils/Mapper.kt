package com.example.bubble.home.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.example.bubble.core.ui.utils.TagUI
import com.example.bubble.domain.model.Tag


fun Long.toTimeUIFormat(): String {
    val timerFormat =
        "${(this / 1000 / 60).toString().padStart(2, '0')}:" +
        "${(this / 1000 % 60).toString().padStart(2, '0')} "

    return timerFormat
}

fun Tag.toTagUI(): TagUI {
    return TagUI(
        id = id,
        name = tagName,
        color = Color(tagColor)
    )
}

fun TagUI.toTag(): Tag{
    return Tag(
        id = id,
        tagName = name!!,
        tagColor = color.toArgb()
    )
}