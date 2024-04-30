package com.example.bubble.award.presentation

import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bubble.award.AwardViewModel
import com.example.bubble.award.R
import com.example.bubble.award.model.AwardState
import com.example.bubble.award.utils.getImageByAwardId
import com.example.bubble.core.ui.theme.BubbleTheme
import com.example.bubble.core.ui.utils.BubbleEmptyDataScreen
import com.example.bubble.core.ui.utils.BubbleErrorScreen
import com.example.bubble.core.ui.utils.BubbleLoadingScreen
import com.example.bubble.core.ui.utils.GradientColumn
import com.example.bubble.domain.model.Award

typealias coreDrawable = com.example.bubble.core.R.drawable

@Composable
fun AwardScreen(
    modifier: Modifier = Modifier,
    viewModel: AwardViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val currentState = state

    viewModel.updateBadgeAward()

    if (currentState != AwardState.DefaultState) {
        GradientColumn(
            accentGradientColor = BubbleTheme.colors.backgroundGradientAwardAccentColor4,
            content = {
                if (currentState is AwardState.IsLoadingState) {
                    LoadingStateScreen()
                }

                if (currentState is AwardState.EmptyDataState) {
                    EmptyDataScreen(message = currentState.message)
                }

                if (currentState is AwardState.ErrorState) {
                    ErrorStateScreen(error = currentState.message)
                }

                if (currentState is AwardState.LoadedAwardsState) {
                    LoadedAwardStateScreen(modifier, currentState.data)
                }
            }
        )
    }
}

@Composable
private fun LoadingStateScreen() {
    BubbleLoadingScreen()
}

@Composable
private fun EmptyDataScreen(
    modifier: Modifier = Modifier,
    message: String
) {
    BubbleEmptyDataScreen(modifier = modifier)
}

@Composable
private fun ErrorStateScreen(
    modifier: Modifier = Modifier,
    error: String
) {
    BubbleErrorScreen(modifier = modifier, errorMessage = error)
}

@Composable
private fun LoadedAwardStateScreen(modifier: Modifier, awards: List<Award>) {
    LazyColumn {
        items(awards) {
            AwardItem(modifier = modifier, item = it)
        }
    }
}

@Composable
private fun AwardItem(
    modifier: Modifier,
    item: Award
) {
    val cardColor = BubbleTheme.colors.awardCardColor
    val context = LocalContext.current

    Card(
        modifier = modifier
            .padding(BubbleTheme.shapes.itemsPadding)
            .clip(BubbleTheme.shapes.cornerStyle)
            .drawBehind {
                if (!item.isUnlocked!!) {
                    drawImage(
                        image = BitmapFactory
                            .decodeResource(context.resources, coreDrawable.ic_lock)
                            .asImageBitmap(),
                        topLeft = Offset(x = size.width / 2f, y = size.height / 3f)
                    )
                }
            },
        colors = CardDefaults.cardColors(cardColor)
    ) {
        Row(
            modifier = modifier
                .padding(BubbleTheme.shapes.basePadding)
                .fillMaxWidth()
                .blur(if (!item.isUnlocked!!) 3.dp else 0.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = getImageByAwardId(item.id!!)),
                contentDescription = null,
                modifier = Modifier
                    .size(32.dp)
                    .weight(0.3f)
            )
            Column(
                modifier = modifier
                    .padding(BubbleTheme.shapes.basePadding)
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(BubbleTheme.shapes.itemsPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = item.name.toString(),
                    style = BubbleTheme.typography.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center,
                    color = BubbleTheme.colors.primaryTextColor
                )

                Text(
                    text = item.title.toString(),
                    style = BubbleTheme.typography.body,
                    maxLines = 2,
                    overflow = TextOverflow.Visible,
                    textAlign = TextAlign.Center,
                    color = BubbleTheme.colors.primaryTextColor
                )
            }
        }
    }
}