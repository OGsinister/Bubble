package com.example.bubble.core.ui.utils

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.bubble.core.R
import com.example.bubble.core.ui.theme.BubbleTheme

@Composable
fun BubbleDialog(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    color: Color,
    tittle: String
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            TextButton(
                onClick = { onDismiss() }
            ) {
                Text(
                    text = stringResource(id = R.string.ok),
                    color = BubbleTheme.colors.primaryTextColor
                )
            }
        },
        title = {
            Text(
                text = tittle,
                style = BubbleTheme.typography.heading,
                color = BubbleTheme.colors.primaryTextColor
            )
        },
        containerColor = color
    )
}