package com.example.bubble.task.model

import com.example.bubble.domain.model.Task

sealed interface TasksState {
    data object DefaultState: TasksState
    data object IsLoadingState: TasksState
    data class EmptyDataState(val message: String): TasksState
    data class LoadedDataState(val data: List<Task>): TasksState
    data class ErrorState(val message: String): TasksState
}