package com.example.bubble.presentation.utils

import android.annotation.SuppressLint
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bubble.MainViewModel
import com.example.bubble.core.ui.theme.BubbleTheme
import com.example.bubble.core.ui.utils.BubbleImage
import com.example.bubble.core.utils.getDominantColor

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun DrawerHeader(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
    navController: NavController
) {
    val user by viewModel.user.collectAsState()

    val userAwardsCount = viewModel.userAwardsCount.collectAsState()
    val dominantColor = getDominantColor(
        LocalContext.current,
        user.image
    )?.rgb

    viewModel.getAwardCount()

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        BubbleTheme.colors.backgroundGradientColorDark1,
                        Color(dominantColor ?: Color.Red.toArgb()),
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
                image = user.image,
                onClick = {}
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp),
            horizontalAlignment = Alignment.Start
        ) {
            user.name.let {
                Text(
                    text = it,
                    style = BubbleTheme.typography.heading,
                    color = BubbleTheme.colors.primaryTextColor,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Text(
                text = "${viewModel.userAwardsCount.value} ${stringResource(id = com.example.bubble.home.R.string.awards)}",
                style = BubbleTheme.typography.smallText,
                color = BubbleTheme.colors.unselectedDefaultColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}