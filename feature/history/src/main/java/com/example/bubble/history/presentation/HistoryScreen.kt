package com.example.bubble.history.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bubble.core.ui.theme.BubbleTheme
import com.example.bubble.domain.model.History
import com.example.bubble.history.HistoryViewModel
import com.example.bubble.history.model.HistoryState

@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier,
    viewModel: HistoryViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val currentState = state

    if(currentState !is HistoryState.DefaultState){
        Column(
            modifier = modifier
                .padding(BubbleTheme.shapes.basePadding)
                .fillMaxSize()
        ){
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
    }
}

@Composable
fun HistoryContentScreen(
    modifier: Modifier,
    history: List<History>
) {
    LazyColumn(modifier = modifier.fillMaxWidth()) {
        items(
            items = history,
            key = { checkNotNull(it.id) }
        ){
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
    Column(modifier = modifier.fillMaxWidth()) {
        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)) {
            Text(text = history.id.toString())
            Text(text = history.isDone.toString())
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
