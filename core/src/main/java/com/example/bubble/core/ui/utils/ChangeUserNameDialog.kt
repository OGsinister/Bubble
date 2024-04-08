package com.example.bubble.core.ui.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.bubble.core.R
import com.example.bubble.core.ui.theme.BubbleTheme

@Composable
fun ChangeUserNameDialog(
    modifier: Modifier = Modifier,
    text: String,
    onValueChange: (String) -> Unit,
    onClick: () -> Unit,
    onDismiss: () -> Unit,
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
            Card(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                colors = CardDefaults.cardColors(Color.Blue)
            ){
                Column(
                    modifier = Modifier
                        .padding(BubbleTheme.shapes.basePadding)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(
                        text = stringResource(id = R.string.type_your_name),
                        style = BubbleTheme.typography.heading,
                        color = BubbleTheme.colors.primaryTextColor,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    BubbleTextField(
                        value = text,
                        onValueChange = onValueChange
                    )

                    Button(
                        onClick = onClick,
                    ) {
                        Text(text = "Отправить")
                    }
                }
            }
        },
        containerColor = Color.Green
    )
}