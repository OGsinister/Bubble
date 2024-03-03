package com.example.bubble.water.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.bubble.core.ui.theme.BubbleTheme
import com.example.bubble.water.R
import com.example.bubble.water.WaterViewModel
import com.example.bubble.water.model.WaterState

@Composable
fun WaterScreen(
    modifier: Modifier = Modifier,
    viewModel: WaterViewModel = hiltViewModel()
) {
    val state = viewModel.waterState.collectAsState()

    /*Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Button(
            onClick = {
                viewModel.addWaterBubble(1)
            }
        ) {
            Text(text = "click")
        }
    }*/

    val isLottiePlaying by remember{
        mutableStateOf(true)
    }

    val lottieSpeed by remember{
        mutableStateOf(1f)
    }
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.bubble_water_animation)
    )
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = isLottiePlaying,
        speed = lottieSpeed,
        restartOnPlay = false
    )

    Column(
        modifier = modifier
            .padding(top = 50.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when(state.value){
            is WaterState.LoadedAwardsState -> {
                Card(
                    modifier = modifier
                        .padding(BubbleTheme.shapes.basePadding)
                        .fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = BubbleTheme.colors.primaryBackgroundColor
                    )
                ){
                    Column(
                        modifier = modifier
                            .padding(BubbleTheme.shapes.basePadding)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Text(
                            text = stringResource(id = R.string.user_pop),
                            style = BubbleTheme.typography.heading,
                            color = BubbleTheme.colors.primaryTextColor
                        )
                    }

                    Box(
                        contentAlignment = Alignment.Center
                    ){
                        LottieAnimation(
                            composition = composition,
                            progress = {
                                progress
                            },
                            modifier = Modifier.size(400.dp)
                        )

                        Text(
                            text = (state.value as WaterState.LoadedAwardsState).data.commonWater.toString(),
                            style = BubbleTheme.typography.cardElement,
                            color = BubbleTheme.colors.primaryTextColor
                        )

                        Column(
                            modifier = modifier
                                .align(Alignment.BottomCenter)
                                .padding(bottom = 10.dp)
                        ) {
                            Text(
                                text = stringResource(id = R.string.popped_bubbles),
                                style = BubbleTheme.typography.heading,
                                color = BubbleTheme.colors.primaryTextColor
                            )
                        }
                    }

                    Column(
                        modifier = modifier
                            .background(BubbleTheme.colors.notificationColor.copy(alpha = 0.3f))
                            .clip(BubbleTheme.shapes.cornerStyle)
                            .padding(BubbleTheme.shapes.itemsPadding)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Text(
                            text = (state.value as WaterState.LoadedAwardsState).data.comparison.toString(),
                            style = BubbleTheme.typography.title,
                            color = BubbleTheme.colors.primaryTextColor
                        )
                    }
                }
            }
            is WaterState.ErrorState -> {
                Text(text = "error (")
            }
            WaterState.IsLoadingState -> {
                CircularProgressIndicator()
            }
            WaterState.DefaultState -> {
                Text("Default")
            }

            is WaterState.EmptyDataState -> {
                Text("Empty")
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun TestAnimationPreview() {
    BubbleTheme {

    }
}