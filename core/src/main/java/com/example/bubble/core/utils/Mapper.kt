package com.example.bubble.core.utils

fun Long.toTimeUIFormat(): String {
    val timerFormat =
        "${(this / 1_000 / 60).toString().padStart(2, '0')}:" +
                "${(this / 1_000 % 60).toString().padStart(2, '0')} "

    return timerFormat
}

fun Long.toValueOnlyTimeUIFormat(): String {
    val timerFormat =
        (this / 1_000 / 60).toString().padStart(2, '0')

    return timerFormat
}