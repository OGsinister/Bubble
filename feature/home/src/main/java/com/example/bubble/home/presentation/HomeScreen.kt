package com.example.bubble.home.presentation

import androidx.compose.animation.Animatable
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector4D
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import com.example.bubble.home.HomeViewModel
import com.example.bubble.home.model.FocusResult
import com.example.bubble.home.model.HomeEvents
import com.example.bubble.home.model.HomeState
import com.example.bubble.home.utils.rememberLifecycleEvent
import kotlin.random.Random

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    val repeatableColorOne = remember {
        Animatable(Color.Magenta)
    }
    val repeatableColorTwo = remember {
        Animatable(Color(0xFFF8EE94))
    }
    val listColors = remember {
        listOf(
            Color(0xFF987683ff),
            Color(0xFF9874369),
            Color(0xFF98822221)
        )
    }
    var isShowBubbleAnimation by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF111111),
                        Color(0xFF121212),
                        Color(0xFF1D4178)
                    )
                )
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (state) {
            HomeState.DefaultState -> {
                DefaultHomeScreen(viewModel)
            }

            HomeState.FocusRunning -> {
                FocusHomeScreen(
                    viewModel = viewModel,
                    repeatableColorOne = repeatableColorOne,
                    repeatableColorTwo = repeatableColorTwo,
                    listColors = listColors
                )
            }
        }

        Button(onClick = {
            isShowBubbleAnimation = !isShowBubbleAnimation
            if(isShowBubbleAnimation) viewModel.event(HomeEvents.RunFocus)
            else viewModel.event(HomeEvents.StopFocus(result = FocusResult.FAIL))

        }) {
            Text(
                text = if(!isShowBubbleAnimation) "Start" else "Stop"
            )
        }
    }
}

@Composable
fun FocusHomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    repeatableColorOne: Animatable<Color, AnimationVector4D>,
    repeatableColorTwo: Animatable<Color, AnimationVector4D>,
    listColors: List<Color>
) {
    val lifecycleEvent = rememberLifecycleEvent()
    val currentTime by viewModel.currentTime.collectAsState()
    val affirmation by viewModel.affirmation.collectAsState()

    val timerFormat =
        "${(currentTime / 1000 / 60).toString().padStart(2, '0')}:" +
                "${(currentTime / 1000 % 60).toString().padStart(2, '0')} "


    LaunchedEffect(key1 = lifecycleEvent) {
        if (lifecycleEvent == Lifecycle.Event.ON_STOP) {
            viewModel.event(HomeEvents.StopFocus(result = FocusResult.FAIL))
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = timerFormat
        )

        Spacer(modifier = Modifier.padding(10.dp))

        AnimatedVisibility(
            visible = affirmation.isVisible,
            enter = scaleIn(),
            exit = scaleOut()
        ) {
            affirmation.affirmationResource?.let {
                Text(text = stringResource(id = it.id))
            }
        }

        Spacer(modifier = Modifier.padding(10.dp))

        CustomCircleAnimation(
            repeatableColorOne = repeatableColorOne,
            repeatableColorTwo = repeatableColorTwo
        )
    }

    LaunchedEffect(Unit) {
        while (true) {
            listColors.forEach {
                repeatableColorOne.animateTo(
                    it,
                    animationSpec = tween(3500)
                )
            }
            repeatableColorTwo.animateTo(
                Color(Random.nextLong().toInt()),
                animationSpec = tween(800)
            )
        }
    }
}

@Composable
fun DefaultHomeScreen(
    viewModel: HomeViewModel
) {
    
}
