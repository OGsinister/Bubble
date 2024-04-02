package com.example.bubble.settings.model

import com.example.bubble.domain.model.Settings

sealed interface SettingsState {
    data object DefaultState: SettingsState
    data object IsLoadingState: SettingsState
    data class LoadedState(val settings: Settings): SettingsState
    data class ErrorState(val message: String?): SettingsState
}