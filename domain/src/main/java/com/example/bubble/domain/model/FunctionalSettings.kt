package com.example.bubble.domain.model

data class Settings(
    val userSettings: UserSettings? = null,
    val isAffirmationOn: Boolean = true,
    val isUserPopBubble: Boolean = true,
)

data class UserSettings(
    val name: String? = "",
    var avatar: Int = 0
)