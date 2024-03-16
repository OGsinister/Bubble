package com.example.bubble.home.presentation

import android.annotation.SuppressLint
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector4D
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import com.example.bubble.core.ui.theme.BubbleTheme
import com.example.bubble.home.HomeViewModel
import com.example.bubble.home.R
import com.example.bubble.home.model.BubbleTimer
import com.example.bubble.home.model.FocusResult
import com.example.bubble.home.model.HomeEvents
import com.example.bubble.home.model.HomeState
import com.example.bubble.home.utils.BubbleButton
import com.example.bubble.home.utils.CustomCircleAnimation
import com.example.bubble.home.utils.TimeBottomSheet
import com.example.bubble.home.utils.rememberLifecycleEvent
import com.example.bubble.home.utils.toTimeUIFormat
import kotlin.random.Random

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val bubbleTimer by viewModel.bubbleTimer.collectAsState()

    val repeatableColorOne = remember { Animatable(Color.Magenta) }
    val repeatableColorTwo = remember { Animatable(Color(0xFF009688)) }
    val listColors = remember { listOf(
            Color(0xFF987683),
            Color(0xFF673AB7),
            Color(0xFF2196F3)
        )
    }

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
                DefaultHomeScreen(
                    viewModel = viewModel,
                    bubbleTimer = bubbleTimer
                )
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

    LaunchedEffect(key1 = lifecycleEvent) {
        if (lifecycleEvent == Lifecycle.Event.ON_STOP) {
            viewModel.event(HomeEvents.StopFocus(result = FocusResult.FAIL))
        }
    }

    Column(
        modifier = modifier
            .fillMaxHeight(0.4f)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(200.dp),
            contentAlignment = Alignment.Center
        ){
            if(affirmation.isVisible){
                affirmation.affirmationResource?.let {
                    Text(
                        modifier = Modifier
                            .align(Alignment.TopCenter),
                        text = stringResource(id = it.id),
                        color = Color.White
                    )
                }
            }

            CustomCircleAnimation(
                repeatableColorOne = repeatableColorOne,
                repeatableColorTwo = repeatableColorTwo,
                onClick = {
                    if(currentTime == 0L){
                        viewModel.event(HomeEvents.StopFocus(result = FocusResult.SUCCESS))
                    }
                }
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.3f),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = currentTime.toTimeUIFormat(),
            color = Color.White,
            style = BubbleTheme.typography.timerText
        )
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.3f)
            .size(170.dp),
        contentAlignment = Alignment.BottomCenter
    ){
        Button(
            onClick = {
                viewModel.event(HomeEvents.StopFocus(result = FocusResult.FAIL))
            }
        ) {
            Text(
                text = "Сдаться",
                color = Color.White
            )
        }
    }

    LaunchedEffect(Unit) {
        while (true) {
            listColors.forEach {
                repeatableColorOne.animateTo(
                    targetValue = it,
                    animationSpec = tween(3500)
                )
            }
            repeatableColorTwo.animateTo(
                Color(Random.nextLong().toInt()),
                animationSpec = tween(3500)
            )
        }
    }
}

@Composable
fun DefaultHomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    bubbleTimer: BubbleTimer
) {
    val showBottomSheet = viewModel.showTimeBottomSheet

    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f),
            contentAlignment = Alignment.Center
        ){
            Image(
                modifier = Modifier
                    .size(256.dp),
                painter = painterResource(id = R.drawable.ic_default_screen_image),
                contentDescription = "Default screen image"
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            Text(
                modifier = Modifier
                    .clickable {
                        showBottomSheet.value = true
                    },
                text = bubbleTimer.millisInFuture.toTimeUIFormat(),
                color = Color.White
            )

            Box(
                modifier = Modifier
                    .size(170.dp)
                    .fillMaxWidth(0.3f),
                contentAlignment = Alignment.Center
            ){
                BubbleButton(
                    onClick = {
                        viewModel.event(HomeEvents.RunFocus)
                    },
                    text = "Начать"
                )
            }
            Text(
                text = "tag",
                color = Color.White
            )
        }
    }

    if(showBottomSheet.value){
        TimeBottomSheet(
            viewModel = viewModel,
            bubbleTimer = bubbleTimer
        )
    }
}
