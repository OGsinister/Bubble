package com.example.bubble.home.utils

fun Long.toTimeUIFormat(): String {
    val timerFormat =
        "${(this / 1000 / 60).toString().padStart(2, '0')}:" +
        "${(this / 1000 % 60).toString().padStart(2, '0')} "

    return timerFormat
}