package com.example.bubble.core.ui.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.bubble.core.ui.theme.BubbleTheme
import com.example.bubble.core.utils.Constants

@Composable
fun BubbleTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholderText: String,
    focusedTextColor: Color
) {
    val color = BubbleTheme.colors.bubbleButtonColor

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        OutlinedTextField(
            value = value,
            onValueChange = {
                onValueChange(it.take(Constants.TEXT_FILED_MAX_LENGTH))
            },
            placeholder = {
                Text(
                    text = placeholderText,
                    style = BubbleTheme.typography.body,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
            label = {
                Text(
                    text = "${value.length} / ${Constants.TEXT_FILED_MAX_LENGTH}",
                    style = BubbleTheme.typography.smallText,
                    color = color,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(end = 10.dp)
                )
            },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = color,
                unfocusedIndicatorColor = color,
                focusedTextColor = focusedTextColor,
                unfocusedTextColor = focusedTextColor,
                focusedContainerColor = Color.Transparent,
                unfocusedLabelColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                focusedPlaceholderColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            ),
            singleLine = true,
            modifier = modifier.background(Color.Transparent)
        )
    }
}