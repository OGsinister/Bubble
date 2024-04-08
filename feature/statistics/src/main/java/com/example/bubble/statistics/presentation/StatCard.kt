package com.example.bubble.statistics.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.bubble.core.ui.theme.BubbleTheme

@Composable
fun StatCard(
    modifier: Modifier = Modifier,
    gradientColor: List<Color>,
    icon: Int,
    title: String,
    text: String
) {
    Column(
        modifier = modifier
            .size(width = 170.dp, height = 140.dp)
            .padding(10.dp)
            .clip(RoundedCornerShape(30.dp))
            .background(
                Brush.linearGradient(
                    colors = gradientColor
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BubbleTheme.shapes.basePadding),
            verticalArrangement = Arrangement.spacedBy(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .size(25.dp)
            )

            Text(
                text = title,
                style = BubbleTheme.typography.body,
                color = BubbleTheme.colors.primaryTextColor
            )

            Text(
                text = text,
                style = BubbleTheme.typography.smallText,
                color = BubbleTheme.colors.primaryTextColor.copy(alpha = 0.5f)
            )
        }
    }
}