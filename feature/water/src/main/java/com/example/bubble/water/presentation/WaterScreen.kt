package com.example.bubble.water.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bubble.water.WaterViewModel
import com.example.bubble.water.model.WaterState

@Composable
fun WaterScreen(
    modifier: Modifier = Modifier,
    viewModel: WaterViewModel = hiltViewModel()
) {
    val state = viewModel.waterState.collectAsState()

    Box(
        modifier = modifier
            .fillMaxWidth()
    ){
        Button(
            onClick = {
                viewModel.addWaterBubble(1)
            }
        ){
            Text(text = "click")
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 150.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when(state.value){
            is WaterState.EmptyDataState -> {
                (state.value as WaterState.EmptyDataState).message?.let {
                    Text(text = it)
                }
            }
            is WaterState.ErrorState -> {
                Text(text = "error")
            }
            is WaterState.LoadedAwardsState -> {
                (state.value as WaterState.LoadedAwardsState).data.commonWater?.let {
                    Text(text = it.toString())
                }

                (state.value as WaterState.LoadedAwardsState).data.title?.let {
                    Text(text = it)
                }

                (state.value as WaterState.LoadedAwardsState).data.comparison?.let {
                    Text(text = it)
                }
            }
            WaterState.DefaultState -> {
                Text(text = "Default")
            }
            WaterState.IsLoadingState -> {
                CircularProgressIndicator()
            }
        }
    }
}