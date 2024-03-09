package com.example.bubble.home.model

sealed interface HomeState {
    data object DefaultState: HomeState
    data object FocusRunning: HomeState
}