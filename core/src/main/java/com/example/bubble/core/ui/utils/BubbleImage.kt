package com.example.bubble.core.ui.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import coil.compose.AsyncImage
import com.example.bubble.core.R
import com.example.bubble.core.ui.theme.BubbleTheme

@Composable
fun BubbleImage(
    modifier: Modifier = Modifier,
    size: Dp,
    image: Bitmap?,
    onClick: () -> Unit
) {
    val context = LocalContext.current
    val defaultImage = BitmapFactory.decodeResource(context.resources, R.drawable.default_user_avatar)

    AsyncImage(
        model = image ?: defaultImage,
        contentDescription = "User image",
        contentScale = ContentScale.Crop,
        placeholder = painterResource(id = R.drawable.default_user_avatar),
        modifier = modifier
            .clip(BubbleTheme.shapes.circleStyle)
            .size(size)
            .clickable {
                onClick()
            }
    )
}