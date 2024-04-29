package com.example.bubble.settings.presentaion

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.bubble.core.ui.utils.BubbleErrorScreen

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    errorMessage: String?
) {
    errorMessage?.let {
        BubbleErrorScreen(modifier = modifier, errorMessage = it)
    }
}