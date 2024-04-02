package com.example.bubble.settings.model

sealed class SettingsEvent {
    data class ChangeUserName(val name: String): SettingsEvent()
    data class ChangeUserAvatar(val avatar: Int): SettingsEvent()
    data class ChangeBubblePopSetting(val isUserPopBubble: Boolean): SettingsEvent()
    data class ChangeAffirmationSetting(val isAffirmationOn: Boolean): SettingsEvent()
}