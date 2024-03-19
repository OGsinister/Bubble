package com.example.bubble.home.presentation

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector4D
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import com.example.bubble.core.ui.theme.BubbleTheme
import com.example.bubble.home.HomeViewModel
import com.example.bubble.home.R
import com.example.bubble.home.model.FocusResult
import com.example.bubble.home.model.HomeEvents
import com.example.bubble.home.utils.CustomCircleAnimation
import com.example.bubble.home.utils.rememberLifecycleEvent
import com.example.bubble.home.utils.toTimeUIFormat
import kotlin.random.Random

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
                .size(250.dp),
            contentAlignment = Alignment.Center
        ) {
            if (affirmation.isVisible) {
                affirmation.affirmationResource?.let {
                    Box(
                        modifier = modifier
                            .size(height = 50.dp, width = 250.dp)
                            .padding(10.dp)
                            .clip(BubbleTheme.shapes.circleStyle)
                            .align(Alignment.TopCenter)
                            .background(BubbleTheme.colors.notificationColor.copy(0.1f)),
                        contentAlignment = Alignment.Center
                    ){
                        Text(
                            text = stringResource(id = it.id),
                            color = Color.White
                        )
                    }
                }
            }

            CustomCircleAnimation(
                repeatableColorOne = repeatableColorOne,
                repeatableColorTwo = repeatableColorTwo,
                onClick = {
                    if (currentTime == 0L) {
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
    ) {
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
    ) {
        Button(
            onClick = {
                viewModel.event(HomeEvents.StopFocus(result = FocusResult.FAIL))
            },
            colors = ButtonDefaults.buttonColors(BubbleTheme.colors.bubbleButtonColor)
        ) {
            Text(
                text = stringResource(id = R.string.give_up),
                style = BubbleTheme.typography.body,
                color = BubbleTheme.colors.primaryTextColor
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
