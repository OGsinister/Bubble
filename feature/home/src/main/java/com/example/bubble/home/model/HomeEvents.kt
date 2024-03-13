package com.example.bubble.home.model

sealed class HomeEvents {
    data object RunFocus:  HomeEvents()
    data class StopFocus(val result: FocusResult): HomeEvents()
    data class SelectTag(val tag: String): HomeEvents()
}

enum class FocusResult(val cause: String){
    FAIL(cause = "fail"),
    SUCCESS(cause = "success")
}
