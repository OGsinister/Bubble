package com.example.bubble.settings.model

import com.example.bubble.core.utils.User

sealed interface UserState {
    data object DefaultState: UserState
    data object IsLoadingState: UserState
    data class EmptyDataState(val message: String): UserState
    data class LoadedState(val settings: User): UserState
    data class ErrorState(val message: String?): UserState
}