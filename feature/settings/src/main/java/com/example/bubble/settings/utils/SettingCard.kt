package com.example.bubble.settings.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.bubble.core.ui.theme.BubbleTheme
import com.example.bubble.settings.model.SettingsEvent

@Composable
fun SettingCard(
    modifier: Modifier = Modifier,
    checked: Boolean,
    onCheckChange: () -> Unit,
    text: String
) {
    Text(
        text = text,
        style = BubbleTheme.typography.body,
        color = BubbleTheme.colors.primaryTextColor,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )

    Switch(
        checked = checked,
        onCheckedChange = {
            onCheckChange()
        }
    )
}