package com.example.bubble.core.ui.theme

import androidx.compose.ui.graphics.Color

data class BubbleColors(
    val primaryBackgroundColor: Color,
    val secondaryBackgroundColor: Color,
    val primaryTextColor: Color,
    val secondaryTextColor: Color,
    val notificationColor: Color,
    val errorColor: Color,
    val bubbleButtonColor: Color,
    val tagBackgroundColor: Color,
    val unselectedDefaultColor: Color,
    val backgroundGradientColorDark1: Color,
    val backgroundGradientColorDark2: Color,
    val backgroundGradientHomeAccentColor1: Color,
    val backgroundGradientNavDrawerAccentColor2: Color,
    val backgroundGradientSettingsAccentColor3: Color,
    val backgroundGradientWaterAccentColor4: Color,
    val backgroundGradientAwardAccentColor4: Color,
    val backgroundGradientHistoryAccentColor4: Color,
    val backgroundGradientStatsAccentColor4: Color,
    val backgroundGradientRelaxAccentColor4: Color,
    val selectedContainerColor: Color,
    val chartBackgroundColor: Color,
    val chartTitleTextColor: Color,
    val chartLineColor: Color,
    val tagChartLineColor: Color,
)

val bubbleLightPalette = BubbleColors(
    primaryBackgroundColor = Color(0xFF3679DE),
    secondaryBackgroundColor = Color(0x66000000),
    primaryTextColor = Color(0xFFFFFFFF),
    secondaryTextColor = Color(0XFF000000),
    notificationColor = Color(0xFF15CCC1),
    errorColor = Color(0xFFFF0606),
    bubbleButtonColor = Color(0xFF15CCC1),
    tagBackgroundColor = Color(0x66FFFFFF),
    unselectedDefaultColor = Color(0xFFB1AFAF),
    backgroundGradientColorDark1 = Color(0xFF111111),
    backgroundGradientColorDark2 = Color(0xFF121212),
    backgroundGradientHomeAccentColor1 = Color(0xFF1D4178),
    backgroundGradientNavDrawerAccentColor2 = Color(0xFF614242),
    backgroundGradientSettingsAccentColor3 = Color(0xFF927C68),
    backgroundGradientWaterAccentColor4 = Color(0xFF947385),
    backgroundGradientAwardAccentColor4 = Color(0xFF627D89),
    backgroundGradientHistoryAccentColor4 = Color(0xFF6B6383),
    backgroundGradientStatsAccentColor4 = Color(0xFF7CA099),
    backgroundGradientRelaxAccentColor4 = Color(0xFF4B614E),
    selectedContainerColor = Color(0x1915CCC1),
    chartBackgroundColor = Color(0xFF211f28),
    chartTitleTextColor = Color(0xFF403f46),
    chartLineColor = Color(0xFF217074),
    tagChartLineColor = Color(0xFFc6d8ff)
)