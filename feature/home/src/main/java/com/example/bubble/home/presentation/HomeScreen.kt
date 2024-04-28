package com.example.bubble.home.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.Animatable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bubble.core.ui.theme.BubbleTheme
import com.example.bubble.core.ui.utils.GradientColumn
import com.example.bubble.home.HomeViewModel
import com.example.bubble.home.model.HomeState

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel<HomeViewModel>()
) {
    val state by viewModel.state.collectAsState()
    val bubbleTimer by viewModel.bubbleTimer.collectAsState()
    val tag by viewModel.tag.collectAsState()

    val repeatableColorOne = remember { Animatable(Color.Magenta) }
    val repeatableColorTwo = remember { Animatable(Color(0xFF673AB7)) }

    val listColors = remember {
        listOf(
            Color(0xFF3F51B5),
            Color(0xFF673AB7),
            Color(0xFF2196F3)
        )
    }

    GradientColumn(
        accentGradientColor = BubbleTheme.colors.backgroundGradientHomeAccentColor1,
        content = {
            when (state) {
                HomeState.DefaultState -> {
                    DefaultHomeScreen(
                        viewModel = viewModel,
                        bubbleTimer = bubbleTimer,
                        tag = tag
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
    )
}