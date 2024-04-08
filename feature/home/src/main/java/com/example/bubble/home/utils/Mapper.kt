package com.example.bubble.home.utils

import androidx.compose.ui.graphics.toArgb
import com.example.bubble.core.ui.utils.TagUI
import com.example.bubble.domain.model.Tag


fun TagUI.toTag(): Tag{
    return Tag(
        id = id,
        tagName = name!!,
        tagColor = color.toArgb(),
        tagIcon = icon
    )
}