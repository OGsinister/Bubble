package com.example.bubble.core.ui.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.bubble.core.ui.theme.BubbleTheme

@Composable
fun GradientColumn(
    modifier: Modifier = Modifier,
    firstDarkColor: Color = BubbleTheme.colors.backgroundGradientColorDark1,
    secondDarkColor: Color = BubbleTheme.colors.backgroundGradientColorDark2,
    accentGradientColor: Color,
    content: @Composable () -> Unit,
    padding: Dp = BubbleTheme.shapes.basePadding,
    paddingValuesTop: PaddingValues = PaddingValues()
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        firstDarkColor,
                        secondDarkColor,
                        accentGradientColor
                    )
                )
            )
            .padding(
                top = paddingValuesTop.calculateTopPadding(),
                start = padding,
                end = padding,
                bottom = padding
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        content()
    }
}