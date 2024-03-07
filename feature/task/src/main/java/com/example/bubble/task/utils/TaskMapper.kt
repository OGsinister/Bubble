package com.example.bubble.task.utils

import com.example.bubble.data.utils.DatabaseResource
import com.example.bubble.domain.model.Task
import com.example.bubble.task.model.TaskState
import com.example.bubble.task.model.TasksState

internal fun DatabaseResource<List<Task>>.toTasksState(): TasksState {
    return when(this){
        is DatabaseResource.Default -> TasksState.DefaultState
        is DatabaseResource.Empty -> TasksState.EmptyDataState(message = message)
        is DatabaseResource.Error -> TasksState.ErrorState(message)
        is DatabaseResource.LoadedData -> TasksState.LoadedDataState(loadedData)
        is DatabaseResource.Loading -> TasksState.IsLoadingState
    }
}

internal fun DatabaseResource<Task>.toTasksState(): TaskState {
    return when(this){
        is DatabaseResource.Default -> TaskState.DefaultState
        is DatabaseResource.Empty -> TaskState.EmptyDataState(message = message)
        is DatabaseResource.Error -> TaskState.ErrorState(message)
        is DatabaseResource.LoadedData -> TaskState.LoadedDataState(loadedData)
        is DatabaseResource.Loading -> TaskState.IsLoadingState
    }
}