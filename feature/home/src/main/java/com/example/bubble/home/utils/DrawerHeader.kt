package com.example.bubble.home.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.bubble.core.ui.theme.BubbleTheme
import com.example.bubble.core.ui.utils.BubbleImage
import com.example.bubble.core.utils.getDominantColor
import com.example.bubble.home.HomeViewModel

@Composable
fun DrawerHeader(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel
){
    val userAvatar = viewModel.getUserAvatar()
        .takeIf { it != 0 } ?: com.example.bubble.core.R.drawable.default_user_avatar
    val dominantColor = getDominantColor(LocalContext.current, userAvatar)?.rgb
    viewModel.getAwardCount()

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        BubbleTheme.colors.backgroundGradientColorDark1,
                        Color(dominantColor!!),
                        BubbleTheme.colors.backgroundGradientColorDark1.copy(0.4f)
                    )
                )
            )
            .padding(BubbleTheme.shapes.itemsPadding),
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .drawBehind {
                    drawCircle(
                        color = Color.Gray
                    )
                },
            contentAlignment = Alignment.Center
        ) {
            BubbleImage(
                size = 56.dp,
                image = userAvatar,
                onClick = {
                    // navigate to settings screen
                }
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp),
            horizontalAlignment = Alignment.Start
        ) {
            viewModel.getUserName()?.let {
                Text(
                    text = it,
                    style = BubbleTheme.typography.heading,
                    color = BubbleTheme.colors.primaryTextColor,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Text(
                text = "userAwardsCount",
                style = BubbleTheme.typography.smallText,
                color = BubbleTheme.colors.unselectedDefaultColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}