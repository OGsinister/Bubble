package com.example.bubble.settings.presentaion

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import com.example.bubble.core.ui.theme.BubbleTheme

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    errorMessage: String?
) {
    errorMessage?.let {
        Text(
            modifier = modifier,
            text = it,
            style = BubbleTheme.typography.heading,
            color = Color.Red,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}