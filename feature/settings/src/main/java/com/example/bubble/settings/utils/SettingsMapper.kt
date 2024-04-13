package com.example.bubble.settings.utils

import com.example.bubble.data.utils.DatabaseResource
import com.example.bubble.core.utils.User
import com.example.bubble.settings.model.UserState

internal fun DatabaseResource<User>.toUserState(): UserState {
    return when(this){
        is DatabaseResource.Default -> UserState.DefaultState
        is DatabaseResource.Empty -> UserState.EmptyDataState(message = message)
        is DatabaseResource.Error -> UserState.ErrorState(message)
        is DatabaseResource.LoadedData -> UserState.LoadedState(settings = loadedData)
        is DatabaseResource.Loading -> UserState.IsLoadingState
    }
}