package com.example.bubble.statistics.presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bubble.core.ui.theme.BubbleTheme
import com.example.bubble.core.ui.utils.BubbleEmptyDataScreen
import com.example.bubble.core.ui.utils.BubbleErrorScreen
import com.example.bubble.core.ui.utils.BubbleLoadingScreen
import com.example.bubble.core.ui.utils.GradientColumn
import com.example.bubble.core.utils.toValueOnlyTimeUIFormat
import com.example.bubble.domain.model.Statistic
import com.example.bubble.statistics.R
import com.example.bubble.statistics.StatisticsViewModel
import com.example.bubble.statistics.model.StatisticsState

@Composable
fun StatisticsScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    viewModel: StatisticsViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val currentState = state

    GradientColumn(
        paddingValuesTop = paddingValues,
        accentGradientColor = BubbleTheme.colors.backgroundGradientStatsAccentColor4,
        content = {
            if (currentState is StatisticsState.EmptyDataState){
                StatEmptyDataScreen()
            }

            if (currentState is StatisticsState.ErrorState){
                StatErrorScreen(message = currentState.message)
            }

            if (currentState is StatisticsState.LoadedDataState){
                StatLoadedDataScreen(statistic = currentState.data)

                Log.d("checkMf", currentState.data.weeklyFocusMainData.toString())
            }

            if (currentState is StatisticsState.LoadingState){
                StatLoadingScreen()
            }
        }
    )
}

@Composable
fun StatLoadedDataScreen(
    modifier: Modifier = Modifier,
    statistic: Statistic
){
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        AllTimeInfoSection(
            avgFocusTime = statistic.avgFocusTime,
            countOfSessions = statistic.countOfSession
        )

        AllTimeBarChartSection(statistic = statistic)

        AllTimeTagPieChartSection()
    }
}

@Composable
fun AllTimeSection(
    modifier: Modifier = Modifier,
    text: String,
    content: @Composable () -> Unit
){
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(BubbleTheme.shapes.basePadding),
        verticalArrangement = Arrangement.SpaceBetween
    ){
        Text(
            text = text,
            style = BubbleTheme.typography.heading,
            color = BubbleTheme.colors.primaryTextColor,
            modifier = Modifier.align(Alignment.Start)
        )

        content()
    }
}

@Composable
fun AllTimeTagPieChartSection(
    modifier: Modifier = Modifier,

){
    AllTimeSection(text = "Статистика по тэгам") {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .size(250.dp)
                .background(Color.Red),
            contentAlignment = Alignment.Center
        ){
            // tags pie chart
        }
    }
}

@Composable
fun AllTimeBarChartSection(
    modifier: Modifier = Modifier,
    statistic: Statistic
){
    AllTimeSection(text = "Статистика за все время") {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .size(250.dp)
                .background(Color.Blue),
            contentAlignment = Alignment.Center
        ){


            // Bar chart content
        }
    }
}

@Composable
fun AllTimeInfoSection(
    modifier: Modifier = Modifier,
    avgFocusTime: Long?,
    countOfSessions: Int?,
){
    val sessionGradientColor = listOf(
        BubbleTheme.colors.backgroundGradientColorDark1,
        BubbleTheme.colors.backgroundGradientHomeAccentColor1
    )

    val timeGradientColor = listOf(
        BubbleTheme.colors.backgroundGradientColorDark1,
        BubbleTheme.colors.backgroundGradientNavDrawerAccentColor2
    )

    val sessionIcon = com.example.bubble.core.R.drawable.ic_tag_home_icon
    val timeIcon = com.example.bubble.core.R.drawable.ic_tag_reading_icon

    AllTimeSection(text = stringResource(id = R.string.all_time)) {
        Row(
            modifier = modifier
                .padding(BubbleTheme.shapes.basePadding)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            StatCard(
                gradientColor = sessionGradientColor,
                icon = sessionIcon,
                title = "$countOfSessions ${stringResource(id = R.string.sessions)}",
                text = stringResource(id = R.string.you_focused)
            )
            StatCard(
                gradientColor = timeGradientColor,
                icon = timeIcon,
                title = "${avgFocusTime?.toValueOnlyTimeUIFormat()} ${stringResource(id = R.string.minutes)}",
                text = "${stringResource(id = R.string.you_focused)} в среднем"
            )
        }
    }
}

@Composable
fun StatEmptyDataScreen(modifier: Modifier = Modifier){
    BubbleEmptyDataScreen(modifier = modifier)
}

@Composable
fun StatErrorScreen(modifier: Modifier = Modifier, message: String){
    BubbleErrorScreen(modifier = modifier, errorMessage = message)
}

@Composable
fun StatLoadingScreen(){
    BubbleLoadingScreen()
}