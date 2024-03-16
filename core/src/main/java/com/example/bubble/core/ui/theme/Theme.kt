package com.example.bubble.core.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp

@Composable
fun BubbleTheme( content: @Composable () -> Unit) {
    CompositionLocalProvider(
        LocalBubbleColors provides bubbleLightPalette,
        LocalBubbleTypography provides bubbleTypography,
        LocalBubbleShapes provides bubbleShapes,
        content = content
    )
}

object BubbleTheme{
    val colors: BubbleColors
        @Composable
        get() = LocalBubbleColors.current

    val typography: BubbleTypography
        @Composable
        get() = LocalBubbleTypography.current

    val shapes: BubbleShapes
        @Composable
        get() = LocalBubbleShapes.current
}

data class BubbleTypography(
    val heading: TextStyle,
    val body: TextStyle,
    val title: TextStyle,
    val cardElement: TextStyle,
    val timerText: TextStyle
)

data class BubbleShapes(
    val basePadding: Dp,
    val itemsPadding: Dp,
    val textPadding: Dp,
    val cornerStyle: Shape
)

val LocalBubbleColors = compositionLocalOf<BubbleColors> {
    error("No color provided")
}

val LocalBubbleTypography = compositionLocalOf<BubbleTypography> {
    error("No typography provided")
}

val LocalBubbleShapes = compositionLocalOf<BubbleShapes> {
    error("No shapes provided")
}