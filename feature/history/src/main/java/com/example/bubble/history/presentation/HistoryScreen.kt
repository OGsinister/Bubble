package com.example.bubble.history.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bubble.core.ui.theme.BubbleTheme
import com.example.bubble.core.ui.utils.GradientColumn
import com.example.bubble.domain.model.History
import com.example.bubble.history.HistoryViewModel
import com.example.bubble.history.model.HistoryState
import com.example.bubble.history.utils.toTagUI

@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier,
    viewModel: HistoryViewModel = hiltViewModel(),
    paddingValuesTop: PaddingValues
) {
    val state by viewModel.state.collectAsState()
    val currentState = state

    if(currentState !is HistoryState.DefaultState){
        GradientColumn(
            paddingValuesTop = paddingValuesTop,
            accentGradientColor = BubbleTheme.colors.backgroundGradientHistoryAccentColor4,
            content = {
                if(currentState is HistoryState.IsLoadingState){
                    HistoryLoadingStateScreen(modifier)
                }

                if(currentState is HistoryState.EmptyDataState){
                    EmptyStateScreen(modifier)
                }

                if(currentState is HistoryState.ErrorState){
                    ErrorStateScreen(modifier, currentState.message)
                }

                if(currentState is HistoryState.LoadedDataState){
                    HistoryContentScreen(
                        modifier = modifier,
                        history = currentState.data
                    )
                }
            }
        )
    }
}

@Composable
fun HistoryContentScreen(
    modifier: Modifier,
    history: List<History>
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(items = history.reversed()){
            HistoryItemScreen(
                modifier = modifier,
                history = it
            )
        }
    }
}

@Composable
fun HistoryItemScreen(
    modifier: Modifier,
    history: History
) {
    val headingTextColor =
        if (history.isDone) BubbleTheme.colors.notificationColor else BubbleTheme.colors.errorColor
    val headingText =
        if (history.isDone) "Успешно" else "Неудачно"

    Column(modifier = modifier.fillMaxWidth()) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BubbleTheme.shapes.itemsPadding),
            colors = CardDefaults.cardColors(Color.White.copy(alpha = 0.05f))
        ){
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = headingText,
                    style = BubbleTheme.typography.heading,
                    color = headingTextColor
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ){
                Text(
                    text = history.bubble.dateTime.toString(),
                    style = BubbleTheme.typography.body,
                    color = BubbleTheme.colors.primaryTextColor
                )

                history.bubble.tag?.let {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .drawBehind {
                                drawCircle(
                                    color = it.toTagUI().color,
                                    radius = size.maxDimension / 2f
                                )
                            },
                        contentAlignment = Alignment.Center
                    ){
                        Icon(
                            painter = painterResource(id = it.tagIcon),
                            contentDescription = "Icon tag",
                            modifier = Modifier.size(24.dp),
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ErrorStateScreen(
    modifier: Modifier,
    errorMessage: String
) {
    Text(text = errorMessage)
}

@Composable
fun EmptyStateScreen(
    modifier: Modifier,
) {
    Text(text = "Empty")
}

@Composable
fun HistoryLoadingStateScreen(
    modifier: Modifier,
) {
    CircularProgressIndicator()
}
