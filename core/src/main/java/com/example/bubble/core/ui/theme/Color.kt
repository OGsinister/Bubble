package com.example.bubble.core.ui.theme

import androidx.compose.ui.graphics.Color

data class BubbleColors(
    val primaryBackgroundColor: Color,
    val secondaryBackgroundColor: Color,
    val primaryTextColor: Color,
    val secondaryTextColor: Color,
    val notificationColor: Color,
    val errorColor: Color,

    //val tagColors: Color,
)

val bubbleLightPalette = BubbleColors(
    primaryBackgroundColor = Color(0xFF3679DE),
    secondaryBackgroundColor = Color(0x66000000),
    primaryTextColor = Color(0xFFFFFFFF),
    secondaryTextColor = Color(0XFF000000),
    notificationColor = Color(0xFF15CCC1),
    errorColor = Color(0xFFFF0606)
)