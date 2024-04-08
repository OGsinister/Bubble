package com.example.bubble.history.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bubble.core.ui.theme.BubbleTheme
import com.example.bubble.core.ui.utils.BubbleEmptyDataScreen
import com.example.bubble.core.ui.utils.BubbleErrorScreen
import com.example.bubble.core.ui.utils.BubbleLoadingScreen
import com.example.bubble.core.ui.utils.GradientColumn
import com.example.bubble.domain.model.History
import com.example.bubble.history.HistoryViewModel
import com.example.bubble.history.model.HistoryState

@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier,
    viewModel: HistoryViewModel = hiltViewModel(),
    paddingValuesTop: PaddingValues
) {
    val state by viewModel.state.collectAsState()
    val currentState = state

    if (currentState !is HistoryState.DefaultState) {
        GradientColumn(
            paddingValuesTop = paddingValuesTop,
            accentGradientColor = BubbleTheme.colors.backgroundGradientHistoryAccentColor4,
            content = {
                if (currentState is HistoryState.IsLoadingState) {
                    HistoryLoadingStateScreen(modifier)
                }

                if (currentState is HistoryState.EmptyDataState) {
                    EmptyStateScreen(modifier)
                }

                if (currentState is HistoryState.ErrorState) {
                    ErrorStateScreen(modifier, currentState.message)
                }

                if (currentState is HistoryState.LoadedDataState) {
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
        items(items = history.reversed()) {
            HistoryItemScreen(
                modifier = modifier,
                history = it,
                firstElement = history.reversed().first(),
                lastElement = history.reversed().last()
            )
        }
    }
}

@Composable
fun ErrorStateScreen(
    modifier: Modifier,
    errorMessage: String
) {
    BubbleErrorScreen(modifier = modifier, errorMessage = errorMessage)
}

@Composable
fun EmptyStateScreen(
    modifier: Modifier,
) {
    BubbleEmptyDataScreen(modifier = modifier)
}

@Composable
fun HistoryLoadingStateScreen(
    modifier: Modifier,
) {
    BubbleLoadingScreen()
}