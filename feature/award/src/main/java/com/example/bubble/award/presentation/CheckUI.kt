package com.example.bubble.award.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bubble.award.AwardViewModel
import com.example.bubble.award.model.AwardState

@Composable
fun CheckUI(
    modifier: Modifier = Modifier,
    viewModel: AwardViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState()
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        when(state.value){
            is AwardState.LoadedAwardsState -> {
                Text(text = "Loaded state")
            }
            is AwardState.EmptyDataState -> {
                Text(text = "EmptyDataState")
            }
            is AwardState.ErrorState -> {
                Text(text = "ErrorState")
            }
            AwardState.DefaultState -> {
                Text(text = "Default")
            }
            AwardState.IsLoadingState -> {
                CircularProgressIndicator()
            }
        }
    }
}
