package com.example.bubble.home.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.bubble.core.ui.theme.BubbleTheme
import com.example.bubble.core.ui.utils.BubbleBottomSheet
import com.example.bubble.home.HomeViewModel
import com.example.bubble.home.R
import com.example.bubble.home.model.BubbleTimer
import com.example.bubble.home.model.HomeEvents
import com.example.bubble.home.model.SelectedTime
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeBottomSheet(
    viewModel: HomeViewModel,
    bubbleTimer: BubbleTimer
) {
    val sheetScope = rememberCoroutineScope()
    val showTimerSheet = viewModel.showTimeBottomSheet
    val sheetState = rememberModalBottomSheetState()

    BubbleBottomSheet(
        onDismiss = {
            showTimerSheet.value = false
        },
        sheetState = sheetState,
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 30.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = stringResource(id = R.string.select_time),
                    style = BubbleTheme.typography.heading,
                    color = BubbleTheme.colors.notificationColor,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
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
                            text = stringResource(id = R.string.save)
                        )
                    }
                }

                LazyVerticalGrid(
                    modifier = Modifier
                        .fillMaxWidth(),
                    columns = GridCells.Adaptive(minSize = 60.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
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
                                    BubbleTheme.colors.notificationColor
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
    )
}