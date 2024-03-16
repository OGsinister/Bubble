package com.example.bubble.home.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.bubble.home.HomeViewModel
import com.example.bubble.home.model.BubbleTimer
import com.example.bubble.home.model.HomeEvents
import com.example.bubble.home.model.SelectedTime
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeBottomSheet(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    bubbleTimer: BubbleTimer
) {
    val sheetScope = rememberCoroutineScope()
    val showTimerSheet = viewModel.showTimeBottomSheet
    val sheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = {
            showTimerSheet.value = false
        },
        sheetState = sheetState
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "Выберите время работы таймера"
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ){
                TextButton(
                    onClick = {
                        sheetScope.launch {
                            sheetState.hide()
                        }.invokeOnCompletion {
                            if(!sheetState.isVisible){
                                showTimerSheet.value = false
                            }
                        }
                    }
                ) {
                    Text(
                        text = "Сохранить"
                    )
                }
            }

            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth(),
                columns = GridCells.Adaptive(minSize = 80.dp),
                verticalArrangement = Arrangement.spacedBy(50.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                contentPadding = PaddingValues(10.dp)
            ) {
                items(SelectedTime.entries){ entries ->
                    TextButton(
                        onClick = {
                            viewModel.event(HomeEvents.SelectTime(entries))
                        },
                        colors = ButtonDefaults.buttonColors(
                            if(entries.time == bubbleTimer.millisInFuture){
                                Color.Blue
                            }else{
                                Color.Gray
                            }
                        )
                    ) {
                        Text(text = entries.time.toTimeUIFormat())
                    }
                }
            }
        }
    }
}