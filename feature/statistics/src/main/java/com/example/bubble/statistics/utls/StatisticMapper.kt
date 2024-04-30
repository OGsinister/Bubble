package com.example.bubble.statistics.utls

import com.example.bubble.data.utils.DatabaseResource
import com.example.bubble.domain.model.Statistic
import com.example.bubble.statistics.model.StatisticsState

internal fun DatabaseResource<Statistic>.toStatisticState(): StatisticsState {
    return when(this){
        is DatabaseResource.Default -> StatisticsState.DefaultState
        is DatabaseResource.Empty -> StatisticsState.EmptyDataState(message)
        is DatabaseResource.Error -> StatisticsState.ErrorState(message)
        is DatabaseResource.LoadedData -> StatisticsState.LoadedDataState(data = loadedData)
        is DatabaseResource.Loading -> StatisticsState.LoadingState
    }
}

/**
 * Маппер дней недели. Из Int в String
 * 0 - Воскресенье
 * 1 - Понедельник
 * 2 - Вторник
 * 3 - Среда
 * 4 - Четверг
 * 5 - Пятница
 * 6 - Суббота
 */
internal fun String.toWeekly(): String {
    return when(this){
        "0" -> "Воскресенье"
        "1" -> "Понедельник"
        "2" -> "Вторник"
        "3" -> "Среда"
        "4" -> "Четверг"
        "5" -> "Пятница"
        else -> "Суббота"
    }
}

internal fun Long.toWeeklyInt(): Int {
    return (this / 1_000 / 60).toInt()
}