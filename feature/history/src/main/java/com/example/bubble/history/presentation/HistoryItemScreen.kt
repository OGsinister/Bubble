package com.example.bubble.history.presentation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.bubble.core.ui.theme.BubbleTheme
import com.example.bubble.core.utils.toTimeUIFormat
import com.example.bubble.domain.model.History
import com.example.bubble.history.R
import com.example.bubble.history.utils.toTagUI

@Composable
fun HistoryItemScreen(
    modifier: Modifier,
    history: History,
    firstElement: History,
    lastElement: History
) {
    val headingTextColor =
        if (history.isDone) BubbleTheme.colors.notificationColor else BubbleTheme.colors.errorColor
    val headingText =
        if (history.isDone) "Успешно" else "Неудачно"

    val historyLineColor =
        if (history.isDone) BubbleTheme.colors.notificationColor else BubbleTheme.colors.errorColor

    val historyIcon =
        if (history.isDone) Icons.Default.Done else Icons.Default.Clear

    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
    ){
        Column(
            modifier = Modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            if (firstElement != history){
                Canvas(modifier = Modifier.fillMaxHeight(0.3f)) {
                    drawLine(
                        color = Color.White,
                        start = Offset(x = size.width / 2f, y = 0f),
                        end = Offset(size.width / 2f , size.height),
                        pathEffect = pathEffect
                    )
                }
            }

            Box(
                modifier = Modifier
                    .padding(top = if (firstElement == history) 30.dp else 0.dp)
                    .drawBehind {
                        drawCircle(
                            color = historyLineColor,
                            radius = size.maxDimension / 2.5f
                        )
                    }
                    .size(40.dp),
                contentAlignment = Alignment.Center
            ){
                Icon(
                    imageVector = historyIcon,
                    contentDescription = null,
                    modifier = Modifier
                        .size(18.dp),
                    tint = Color.White
                )
            }

            if (lastElement != history){
                Canvas(modifier = Modifier.fillMaxHeight(1f)) {
                    drawLine(
                        color = Color.White,
                        start = Offset(x = size.width / 2f, y = 0f),
                        end = Offset(size.width / 2f , size.height),
                        pathEffect = pathEffect
                    )
                }
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BubbleTheme.shapes.itemsPadding),
            colors = CardDefaults.cardColors(Color.White.copy(alpha = 0.05f))
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = headingText,
                    style = BubbleTheme.typography.heading,
                    color = headingTextColor
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 0.dp, bottom = 5.dp, start = 15.dp, end = 15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                history.bubble.focusTime?.let {
                    Text(
                        text = "${it.toTimeUIFormat()} ${stringResource(id = R.string.minutes)}",
                        style = BubbleTheme.typography.body,
                        color = BubbleTheme.colors.primaryTextColor
                    )
                }

                history.bubble.tag?.let {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .drawBehind {
                                drawCircle(
                                    color = it.toTagUI().color,
                                    radius = size.maxDimension / 2.5f
                                )
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = it.tagIcon),
                            contentDescription = "Icon tag",
                            modifier = Modifier.size(18.dp),
                            tint = Color.White
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.padding(5.dp))
        }
    }
}