package com.example.bubble.home.presentation

import android.os.CountDownTimer
import android.os.SystemClock
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bubble.home.HomeViewModel
import com.example.bubble.home.model.HomeEvents
import com.example.bubble.home.model.HomeState
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

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
                // Start animation
            }
        }
    }
}

@Composable
fun FocusHomeScreen(viewModel: HomeViewModel) {

    val currentTime by timer(5000L)
    Text(
        text = currentTime.toString()
    )
    
    Spacer(modifier = Modifier.padding(10.dp))

    Button(onClick = {
        viewModel.event(HomeEvents.StopFocus)
    }) {
        Text("stop")
    }
}

@Composable
fun timer(
    initialMillis: Long,
    step: Long = 1000L
): MutableState<Long>{
    val timeLeft = remember {
        mutableLongStateOf(initialMillis)
    }

    LaunchedEffect(initialMillis, step) {
        val startTime = SystemClock.uptimeMillis()
        while (isActive && timeLeft.longValue > 0){
            val duration = (SystemClock.uptimeMillis() - startTime).coerceAtLeast(0)
            timeLeft.longValue = (initialMillis - duration).coerceAtLeast(0)
            delay(1000L)
        }
    }
    return timeLeft
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
