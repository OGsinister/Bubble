package com.example.bubble.home.model

import android.os.CountDownTimer

data class BubbleTimer(
    var timer: CountDownTimer? = null,
    var isActive: Boolean = false,
    val millisInFuture: Long = SelectedTime.TEN_MINUTES.time
)

enum class SelectedTime(val time: Long){
    TEN_MINUTES(600_000L),
    FIFTEEN_MINUTES(900_000L),
    TWENTY_MINUTES(1_200_000L),
    TWENTY_FIVE_MINUTES(1_500_000L),
    THIRTY_MINUTES(1_800_000L),
    THIRTY_FIVE_MINUTES(2_100_000L),
    FORTY_MINUTES(2_400_000L),
    FORTY_FIVE_MINUTES(2_700_000L),
    FIFTY_MINUTES(3_000_000L),
    FIFTY_FIVE_MINUTES(3_300_000L),
    ONE_HOUR(3_600_000L)
}