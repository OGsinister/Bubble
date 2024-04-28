package com.example.bubble.relax.presentation

import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bubble.core.ui.theme.BubbleTheme
import com.example.bubble.core.ui.utils.BubbleErrorScreen
import com.example.bubble.core.ui.utils.BubbleLoadingScreen
import com.example.bubble.core.ui.utils.GradientColumn
import com.example.bubble.relax.RelaxViewModel
import kotlin.random.Random

@Composable
fun RelaxScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    viewModel: RelaxViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val currentState = state

    val bubbles by viewModel.state.collectAsState()
    val infiniteTransition = rememberInfiniteTransition(label = "")

    if (bubbles.isEmpty()) {
        viewModel.addBubble()
    }

    GradientColumn(
        paddingValuesTop = paddingValues,
        accentGradientColor = BubbleTheme.colors.backgroundGradientStatsAccentColor4,
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .drawBehind {
                        bubbles.forEach {
                            drawCircle(
                                color = it.color,
                                radius = it.radius.toFloat(),
                                center = Offset(
                                    x = Random.nextInt(size.width.toInt()).toFloat(),
                                    y = Random.nextInt(size.height.toInt()).toFloat()
                                )
                            )
                        }
                    }
            )

            /*when(currentState){
                RelaxState.IsLoadingState -> RelaxIsLoadingScreen()
                is RelaxState.DefaultState -> RelaxDefaultScreen()
                is RelaxState.ErrorDataState -> RelaxErrorScreen(errorMessage = currentState.errorMessage ?: "Ошибка")
                is RelaxState.LoadedDataState -> RelaxLoadedDataScreen()
            }*/
        }
    )
}

@Composable
fun RelaxBubbleItem(
    modifier: Modifier = Modifier,
    x: Float,
    y: Float,
    onClick: () -> Unit,
    color: Color
){
    Box(
        modifier = modifier
            .size(50.dp)
            .offset(x.dp, y.dp)
            .clip(CircleShape)
            .background(color)
            .clickable { onClick() }
    )
}

@Composable
fun RelaxDefaultScreen() {

}

@Composable
fun RelaxIsLoadingScreen() {
    BubbleLoadingScreen()
}

@Composable
fun RelaxErrorScreen(
    modifier: Modifier = Modifier,
    errorMessage: String
) {
    BubbleErrorScreen(modifier = modifier, errorMessage = errorMessage)
}

@Composable
fun RelaxLoadedDataScreen() {

}
