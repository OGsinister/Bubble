package com.example.bubble.home.utils

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.bubble.home.HomeViewModel

@Composable
fun BubbleButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String
) {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val minPulsingSize = 120.dp
    val maxPulsingSize = 160.dp
    val durationMillis = 2_000
    val bubbleButtonSize by infiniteTransition.animateFloat(
        initialValue = minPulsingSize.value,
        targetValue = maxPulsingSize.value,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = ""
    )
    val bubbleButtonAlpha by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = ""
    )
    Box(
        contentAlignment = Alignment.Center
    ){
        Button(
            modifier = modifier
                .clip(CircleShape)
                .size(bubbleButtonSize.dp)
                .alpha(bubbleButtonAlpha),
            onClick = {
                onClick()
            }
        ) {}
        Button(
            modifier = modifier
                .clip(CircleShape)
                .size(minPulsingSize),
            onClick = {
                onClick()
            }
        ) {
            Text(
                text = text,
                color = Color.White
            )
        }
    }
}