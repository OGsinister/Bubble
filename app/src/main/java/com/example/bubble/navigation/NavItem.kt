package com.example.bubble.navigation

import androidx.compose.ui.graphics.painter.Painter

data class NavItem(
    val route: String,
    val title: String,
    val icon: Painter
)