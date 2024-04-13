package com.example.bubble.settings.model

import android.graphics.Bitmap

sealed class SettingsEvent {
    data class ChangeUserName(val name: String): SettingsEvent()
    data class ChangeUserAvatar(val avatar: Bitmap): SettingsEvent()
    data class ChangeBubblePopSetting(val isUserPopBubble: Boolean): SettingsEvent()
    data class ChangeAffirmationSetting(val isAffirmationOn: Boolean): SettingsEvent()
    data class ChangeSoundSetting(val isSoundOn: Boolean): SettingsEvent()
}