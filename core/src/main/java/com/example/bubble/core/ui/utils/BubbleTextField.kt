package com.example.bubble.core.ui.utils

import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun BubbleTextField(
    modifier: Modifier = Modifier,
    value: String
) {
    OutlinedTextField(
        value = value,
        onValueChange = {

        }
    )
}