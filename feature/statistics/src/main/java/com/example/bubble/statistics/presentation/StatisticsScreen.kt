package com.example.bubble.statistics.presentation

import android.util.Log
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.toPath
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bubble.core.ui.theme.BubbleTheme
import com.example.bubble.core.ui.utils.BubbleEmptyDataScreen
import com.example.bubble.core.ui.utils.BubbleErrorScreen
import com.example.bubble.core.ui.utils.BubbleLoadingScreen
import com.example.bubble.core.ui.utils.GradientColumn
import com.example.bubble.core.utils.toValueOnlyTimeUIFormat
import com.example.bubble.domain.model.FocusTag
import com.example.bubble.domain.model.Statistic
import com.example.bubble.statistics.R
import com.example.bubble.statistics.StatisticsViewModel
import com.example.bubble.statistics.model.StatisticsState
import com.example.bubble.statistics.utls.toWeekly
import com.example.bubble.statistics.utls.toWeeklyInt
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.CartesianChartHost
import com.patrykandpatrick.vico.compose.chart.layer.rememberColumnCartesianLayer
import com.patrykandpatrick.vico.compose.chart.layer.rememberLineCartesianLayer
import com.patrykandpatrick.vico.compose.chart.layer.rememberLineSpec
import com.patrykandpatrick.vico.compose.chart.rememberCartesianChart
import com.patrykandpatrick.vico.compose.component.rememberLineComponent
import com.patrykandpatrick.vico.compose.component.rememberTextComponent
import com.patrykandpatrick.vico.compose.component.shape.shader.color
import com.patrykandpatrick.vico.core.axis.AxisPosition
import com.patrykandpatrick.vico.core.axis.formatter.AxisValueFormatter
import com.patrykandpatrick.vico.core.chart.layer.ColumnCartesianLayer
import com.patrykandpatrick.vico.core.chart.values.AxisValueOverrider
import com.patrykandpatrick.vico.core.component.shape.Shapes
import com.patrykandpatrick.vico.core.component.shape.shader.DynamicShader
import com.patrykandpatrick.vico.core.component.shape.shader.DynamicShaders
import com.patrykandpatrick.vico.core.model.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.model.columnSeries
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

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
                StatLoadedDataScreen(statistic = currentState.data)
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

        TagChartSection(tagsFocusData = statistic.tagFocusData!!)

        SuccessPercentSection(statistic = statistic)
    }
}

@Composable
fun SuccessPercentSection(
    modifier: Modifier = Modifier,
    statistic: Statistic
) {
    val successFocus = remember { statistic.successPercent ?: 1F }
    val allFocus = remember { statistic.allFocusCounts ?: 1 }

    val infiniteTransition = rememberInfiniteTransition(label = "")
    val color by infiniteTransition.animateColor(
        initialValue = Color(0xFF124241),
        targetValue = Color(0xFF2755B1),
        animationSpec = infiniteRepeatable(
            animation = tween(5_000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )
    val secondColor by infiniteTransition.animateColor(
        initialValue = Color(0xFF89FC22),
        targetValue = Color(0xFF90EFF1),
        animationSpec = infiniteRepeatable(
            animation = tween(5_000, easing = EaseInOut),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )
    val cornerRounding by infiniteTransition.animateFloat(
        initialValue = 120f,
        targetValue = 150f,
        animationSpec = infiniteRepeatable(
            animation = tween(3_000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    AllTimeSection(text = stringResource(id = R.string.success_focus_percent)) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .size(250.dp)
                .drawWithCache {
                    val rectangle = RoundedPolygon(
                        numVertices = 3,
                        radius = size.minDimension / 2f,
                        centerX = size.width / 2f,
                        centerY = size.height / 2f,
                        rounding = CornerRounding(cornerRounding)
                    )

                    onDrawBehind {
                        drawPath(
                            rectangle
                                .toPath()
                                .asComposePath(),
                            brush = Brush.radialGradient(listOf(color, secondColor))
                        )
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            if (statistic.allFocusCounts != 0) {
                val successPercent =
                    (successFocus / allFocus) * 100

                val format = "%.2f".format(successPercent)

                Text(
                    text = "$format %",
                    style = BubbleTheme.typography.heading,
                    color = BubbleTheme.colors.primaryTextColor,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
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
fun TagChartSection(
    modifier: Modifier = Modifier,
    tagsFocusData: List<FocusTag>,
) {
    val modelProducer = remember { CartesianChartModelProducer.build() }
    val tagList: MutableList<String> = mutableListOf()
    val valuesList: MutableList<Int> = mutableListOf()

    tagsFocusData.forEach {
        it.tag?.forEach { tagName ->
            tagList.add(stringResource(id = tagName.tagName))
            valuesList.add(tagName.totalTime.toWeeklyInt())
        }
    }

    val bottomAxisValueFormatter =
        AxisValueFormatter<AxisPosition.Horizontal.Bottom> { x, _, _ ->
            tagList[x.toInt() % tagList.size]
        }

    LaunchedEffect(Unit) {
        withContext(Dispatchers.Default) {
            modelProducer.tryRunTransaction {
                columnSeries { series(valuesList) }
            }
        }
    }


    AllTimeSection(text = "Статистика по тэгам") {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = BubbleTheme.shapes.basePadding)
                .clip(BubbleTheme.shapes.cornerStyle)
                .background(BubbleTheme.colors.chartBackgroundColor.copy(alpha = 0.4f)),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            CartesianChartHost(
                chart = rememberCartesianChart(
                    rememberColumnCartesianLayer(
                        columnProvider = ColumnCartesianLayer.ColumnProvider.series(
                            rememberLineComponent(
                                color = Color(0xffd877d8),
                                thickness = 5.dp,
                                shape = Shapes.roundedCornerShape(10)
                            )
                        )
                    ),
                    rememberLineCartesianLayer(
                        lines = listOf(rememberLineSpec(shader = DynamicShaders.color(Color.Magenta))),
                    ),
                    startAxis =
                        rememberStartAxis(
                            label = rememberTextComponent(
                            color = BubbleTheme.colors.chartTitleTextColor
                        )
                    ),
                    bottomAxis = rememberBottomAxis(
                        valueFormatter = bottomAxisValueFormatter,
                        label = rememberTextComponent(
                            color = Color.White
                        ),
                        guideline = null
                    )
                ),
                modelProducer = modelProducer,
                modifier = modifier
                    .padding(BubbleTheme.shapes.basePadding)
            )
        }
    }
}

@Composable
fun BarChartSection(
    modifier: Modifier = Modifier,
    statistic: Statistic
) {
    val modelProducer = remember { CartesianChartModelProducer.build() }
    val daysOfWeek: MutableList<String> = mutableListOf()
    statistic.weeklyFocusMainData?.forEach {
        daysOfWeek.add(it.dayOfWeek)
    }
    val values = statistic.weeklyFocusMainData?.map { it.totalTime.toWeeklyInt() } ?: emptyList()

    val bottomAxisValueFormatter =
        AxisValueFormatter<AxisPosition.Horizontal.Bottom> { x, _, _ ->
            daysOfWeek[x.toInt() % daysOfWeek.size].toWeekly()
        }

    LaunchedEffect(Unit) {
        withContext(Dispatchers.Default) {
            modelProducer.tryRunTransaction {
                columnSeries { series(values) }
            }
        }
    }

    AllTimeSection(text = "Статистика за все время") {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = BubbleTheme.shapes.basePadding)
                .clip(BubbleTheme.shapes.cornerStyle)
                .background(BubbleTheme.colors.chartBackgroundColor.copy(alpha = 0.4f)),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = stringResource(id = R.string.total_time), color = Color.White)
                Text(
                    text = "${statistic.weeklyFocusTime?.toValueOnlyTimeUIFormat()} ${stringResource(
                        id = R.string.minutes
                    )}",
                    color = Color.White
                )
            }

            CartesianChartHost(
                chart = rememberCartesianChart(
                    rememberColumnCartesianLayer(
                        columnProvider = ColumnCartesianLayer.ColumnProvider.series(
                            rememberLineComponent(
                                color = Color(0xffd877d8),
                                thickness = 5.dp,
                                shape = Shapes.roundedCornerShape(10)
                            )
                        ),
                        mergeMode = { ColumnCartesianLayer.MergeMode.Stacked }
                    ),
                    startAxis = rememberStartAxis(
                        label = rememberTextComponent(
                            color = BubbleTheme.colors.chartTitleTextColor
                        )
                    ),
                    bottomAxis = rememberBottomAxis(
                        valueFormatter = bottomAxisValueFormatter,
                        label = rememberTextComponent(
                            color = Color.White
                        ),
                        guideline = null
                    )
                ),
                modelProducer = modelProducer,
                modifier = modifier
                    .padding(BubbleTheme.shapes.basePadding)
            )
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