package com.example.bubble.relax.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.bubble.core.ui.theme.BubbleTheme
import com.example.bubble.core.ui.utils.GradientColumn

@Composable
fun RelaxScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues
) {
    GradientColumn(
        padding = paddingValues.calculateTopPadding(),
        accentGradientColor = BubbleTheme.colors.backgroundGradientStatsAccentColor4,
        content = {
            Text(text = "Relax")
        }
    )
}