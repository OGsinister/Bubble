package com.example.bubble.home.model

sealed class HomeEvents {
    data object RunFocus:  HomeEvents()
    data object StopFocus: HomeEvents()
}