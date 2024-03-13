package com.example.bubble.home.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.bubble.home.HomeViewModel
import com.example.bubble.home.R
import com.example.bubble.home.model.FocusResult
import com.example.bubble.home.model.HomeEvents
import com.example.bubble.home.model.HomeState
import com.example.bubble.home.utils.rememberLifecycleEvent

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when(state){
            HomeState.DefaultState -> {
                DefaultHomeScreen(viewModel)
            }
            HomeState.FocusRunning -> {
                FocusHomeScreen(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun FocusHomeScreen(viewModel: HomeViewModel) {
    val animationState = remember{ mutableStateOf(true) }
    val lifecycleEvent = rememberLifecycleEvent()

    val currentTime by viewModel.currentTime.collectAsState()
    val isLottiePlaying by remember{ mutableStateOf(true) }
    val lottieSpeed by remember{ mutableStateOf(1f) }
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.lto4qjh6))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = isLottiePlaying,
        speed = lottieSpeed,
        restartOnPlay = false
    )
    val affirmation by viewModel.affirmation.collectAsState()

    val timerFormat =
        "${(currentTime / 1000 / 60).toString().padStart(2, '0')}:" +
        "${(currentTime / 1000 % 60).toString().padStart(2, '0')} "

    LaunchedEffect(key1 = lifecycleEvent) {
        if (lifecycleEvent == Lifecycle.Event.ON_STOP){
            viewModel.event(HomeEvents.StopFocus(result = FocusResult.FAIL))
        }
    }
    
    Text(
        text = timerFormat
    )
    
    Spacer(modifier = Modifier.padding(10.dp))

    AnimatedVisibility(
        visible = affirmation.isVisible,
        enter = scaleIn(),
        exit = scaleOut()
    ) {
        Text(
            text = stringResource(id = affirmation.affirmationResource.id)
        )
    }

    AnimatedVisibility(
        visible = animationState.value,
        enter = scaleIn(),
        exit = scaleOut()
    ) {
        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier
                .size(250.dp)
                .clickable {
                    if (currentTime < 1_000L) {
                        animationState.value = !animationState.value
                        // make sound

                        // send event
                        //viewModel.event(HomeEvents.StopFocus(result = FocusResult.SUCCESS))
                    }
                }
        )
    }

    Button(onClick = {
       animationState.value = !animationState.value
       viewModel.event(HomeEvents.StopFocus(result = FocusResult.FAIL))
    }) {
        Text("stop")
    }
}

@Composable
fun DefaultHomeScreen(
    viewModel: HomeViewModel
) {
    Button(onClick = {
        viewModel.event(HomeEvents.RunFocus)
    }) {
        Text(text = "start")
    }
}
