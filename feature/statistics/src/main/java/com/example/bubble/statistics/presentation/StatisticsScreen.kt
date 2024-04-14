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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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
import com.example.bubble.domain.model.WeeklyFocus
import com.example.bubble.statistics.R
import com.example.bubble.statistics.StatisticsViewModel
import com.example.bubble.statistics.model.StatisticsState
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.CartesianChartHost
import com.patrykandpatrick.vico.compose.chart.layer.rememberColumnCartesianLayer
import com.patrykandpatrick.vico.compose.chart.layer.rememberLineCartesianLayer
import com.patrykandpatrick.vico.compose.chart.layer.rememberLineSpec
import com.patrykandpatrick.vico.compose.chart.rememberCartesianChart
import com.patrykandpatrick.vico.compose.component.shape.shader.color
import com.patrykandpatrick.vico.compose.theme.ProvideVicoTheme
import com.patrykandpatrick.vico.compose.theme.VicoTheme
import com.patrykandpatrick.vico.core.axis.AxisPosition
import com.patrykandpatrick.vico.core.axis.formatter.AxisValueFormatter
import com.patrykandpatrick.vico.core.component.shape.shader.DynamicShader
import com.patrykandpatrick.vico.core.component.shape.shader.DynamicShaders
import com.patrykandpatrick.vico.core.model.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.model.ExtraStore
import com.patrykandpatrick.vico.core.model.columnSeries
import com.patrykandpatrick.vico.core.model.lineSeries
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.random.Random

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
            if (currentState is StatisticsState.EmptyDataState) {
                StatEmptyDataScreen()
            }

            if (currentState is StatisticsState.ErrorState) {
                StatErrorScreen(message = currentState.message)
            }

            if (currentState is StatisticsState.LoadedDataState) {
                Log.d("checkMf", currentState.data.toString())

                LazyColumn {
                    items(currentState.data){
                        StatLoadedDataScreen(statistic = it)
                    }
                }
            }

            if (currentState is StatisticsState.LoadingState) {
                StatLoadingScreen()
            }
        }
    )
}

@Composable
fun StatLoadedDataScreen(
    modifier: Modifier = Modifier,
    statistic: Statistic
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        InfoSection(
            avgFocusTime = statistic.avgFocusTime,
            countOfSessions = statistic.countOfSession
        )

        BarChartSection(statistic = statistic)

        TagPieChartSection()

        SuccessPercentSection(statistic = statistic)
    }
}

@Composable
fun SuccessPercentSection(
    modifier: Modifier = Modifier,
    statistic: Statistic
) {
    AllTimeSection(text = "Статистика по тэгам") {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .background(Color.Red),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = statistic.successPercent?.toString() + " %"
            )
        }
    }
}

@Composable
fun AllTimeSection(
    modifier: Modifier = Modifier,
    text: String,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(BubbleTheme.shapes.basePadding),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
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
fun TagPieChartSection(
    modifier: Modifier = Modifier,

    ) {
    AllTimeSection(text = "Статистика по тэгам") {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .size(250.dp)
                .background(Color.Red),
            contentAlignment = Alignment.Center
        ) {
            // tags pie chart
        }
    }
}

@Composable
fun BarChartSection(
    modifier: Modifier = Modifier,
    statistic: Statistic
) {

    val modelProducer = remember { CartesianChartModelProducer.build() }

    LaunchedEffect(Unit) {
        withContext(Dispatchers.Default) {
            modelProducer.tryRunTransaction {
                columnSeries { statistic.weeklyFocusMainData?.totalTime?.let { series(it) } }
            }
        }
    }

    AllTimeSection(text = "Статистика за все время") {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            CartesianChartHost(
                chart = rememberCartesianChart(
                    rememberColumnCartesianLayer(),
                    startAxis = rememberStartAxis(),
                    bottomAxis = rememberBottomAxis()
                ),
                modelProducer = modelProducer,
                modifier = modifier
            )
            // Bar chart content
        }
    }
}

@Composable
fun InfoSection(
    modifier: Modifier = Modifier,
    avgFocusTime: Long?,
    countOfSessions: Int?,
) {
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
        ) {
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
fun StatEmptyDataScreen(modifier: Modifier = Modifier) {
    BubbleEmptyDataScreen(modifier = modifier)
}

@Composable
fun StatErrorScreen(modifier: Modifier = Modifier, message: String) {
    BubbleErrorScreen(modifier = modifier, errorMessage = message)
}

@Composable
fun StatLoadingScreen() {
    BubbleLoadingScreen()
}