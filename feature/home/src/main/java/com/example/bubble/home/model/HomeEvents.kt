package com.example.bubble.home.model

import com.example.bubble.domain.model.Tag

sealed class HomeEvents {
    data object RunFocus:  HomeEvents()
    data class StopFocus(val result: FocusResult): HomeEvents()
    data class SelectTag(val tag: Tag): HomeEvents()
    data class SelectTime(val time: SelectedTime): HomeEvents()
}

enum class FocusResult(val value: Boolean){
    FAIL(value = false),
    SUCCESS(value = true)
}
