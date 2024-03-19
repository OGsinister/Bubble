package com.example.bubble.home.utils

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.bubble.core.ui.theme.BubbleTheme

@Composable
fun BubbleButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String
) {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val minPulsingSize = 120.dp
    val maxPulsingSize = 150.dp
    val durationMillis = 2_000
    val bubbleButtonSize by infiniteTransition.animateFloat(
        initialValue = minPulsingSize.value,
        targetValue = maxPulsingSize.value,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis, easing = FastOutLinearInEasing),
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
        modifier = Modifier
            .size(maxPulsingSize),
        contentAlignment = Alignment.Center
    ){
        Button(
            modifier = modifier
                .clip(CircleShape)
                .size(bubbleButtonSize.dp)
                .alpha(bubbleButtonAlpha),
            onClick = {
                onClick()
            },
            colors = ButtonDefaults.buttonColors(BubbleTheme.colors.bubbleButtonColor)
        ) {}
        Button(
            modifier = modifier
                .clip(CircleShape)
                .size(minPulsingSize),
            onClick = {
                onClick()
            },
            colors = ButtonDefaults.buttonColors(BubbleTheme.colors.bubbleButtonColor)
        ) {
            Text(
                text = text,
                color = Color.White
            )
        }
    }
}