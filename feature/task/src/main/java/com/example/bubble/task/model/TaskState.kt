package com.example.bubble.task.model

import com.example.bubble.domain.model.Task

sealed interface TaskState {
    data object DefaultState: TaskState
    data object IsLoadingState: TaskState
    data class EmptyDataState(val message: String): TaskState
    data class LoadedDataState(val data: Task): TaskState
    data class ErrorState(val message: String): TaskState
}