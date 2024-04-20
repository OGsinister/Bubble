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
 * 0 - Понедельник
 * 1 - Вторник
 * 2 - Среда
 * 3 - Четверг
 * 4 - Пятница
 * 5 - Суббота
 * 6 - Воскресенье
 */
internal fun String.toWeekly(): String {
    return when(this){
        "0" -> "Понедельник"
        "1" -> "Вторник"
        "2" -> "Среда"
        "3" -> "Четверг"
        "4" -> "Пятница"
        "5" -> "Суббота"
        else -> "Воскресенье"
    }
}

internal fun Long.toWeeklyInt(): Int {
    return (this / 1_000 / 60).toInt()
}