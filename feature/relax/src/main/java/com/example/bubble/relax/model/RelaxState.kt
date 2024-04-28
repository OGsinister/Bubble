package com.example.bubble.relax.model

sealed interface RelaxState {
    data object IsLoadingState: RelaxState
    data class DefaultState(val bubblesCount: Int = Constants.DEFAULT_BUBBLES_COUNT): RelaxState
    data class LoadedDataState(val bubblesCount: Int): RelaxState
    data class ErrorDataState(val errorMessage: String? = null): RelaxState
}