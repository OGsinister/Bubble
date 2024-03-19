package com.example.bubble.home.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.bubble.core.ui.theme.BubbleTheme
import com.example.bubble.core.utils.getDominantColor
import com.example.bubble.home.HomeViewModel
import com.example.bubble.home.R
@Composable
fun DrawerHeader(
    modifier: Modifier = Modifier,
    //viewModel: HomeViewModel
){
    val image1 = R.drawable.ic_one
    val image2 = R.drawable.ic_two
    val image3 = R.drawable.ic_three
    val dominantColor = getDominantColor(LocalContext.current, image3)?.rgb
    //val user by viewModel.user.collectAsState()

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
            Image(
                modifier = Modifier
                    .size(56.dp)
                    .clip(BubbleTheme.shapes.circleStyle),
                painter = painterResource(id = image3),
                contentDescription = "User image",
                contentScale = ContentScale.Crop
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Иван Иванов",
                style = BubbleTheme.typography.heading,
                color = BubbleTheme.colors.primaryTextColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = "Награды: 4/5",
                style = BubbleTheme.typography.smallText,
                color = BubbleTheme.colors.unselectedDefaultColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}