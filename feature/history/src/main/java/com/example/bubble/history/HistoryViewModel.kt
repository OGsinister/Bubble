package com.example.bubble.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bubble.data.local.database.dbo.HistoryEntity
import com.example.bubble.data.repository.HistoryRepository
import com.example.bubble.history.model.HistoryState
import com.example.bubble.history.useCase.GetHistoryUseCase
import com.example.bubble.history.utils.toHistoryState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getHistoryUseCase: GetHistoryUseCase,
    private val repository: HistoryRepository
): ViewModel() {

    internal val state: StateFlow<HistoryState> = getHistoryUseCase()
        .map {
            it.toHistoryState()
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, HistoryState.DefaultState)

    /*internal fun addHistory(historyEntity: HistoryEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addBubbleToHistory(historyEntity)
        }
    }*/
}