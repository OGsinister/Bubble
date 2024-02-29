package com.example.bubble.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bubble.history.model.HistoryState
import com.example.bubble.history.useCase.GetHistoryUseCase
import com.example.bubble.history.utils.toHistoryState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getHistoryUseCase: GetHistoryUseCase
): ViewModel() {

    internal val state: Flow<HistoryState> = getHistoryUseCase()
        .map {
            it.toHistoryState()
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, HistoryState.DefaultState)

}