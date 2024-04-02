package com.example.bubble.core.ui.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.bubble.core.ui.theme.BubbleTheme

@Composable
fun BubbleImage(
    modifier: Modifier = Modifier,
    size: Dp,
    image: Int,
    onClick: () -> Unit
) {
    Image(
        painter = painterResource(id = image),
        contentDescription = "User image",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .clip(BubbleTheme.shapes.circleStyle)
            .size(size)
            .clickable {
                onClick()
            }
    )
}