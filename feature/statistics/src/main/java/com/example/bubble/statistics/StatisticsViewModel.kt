package com.example.bubble.statistics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bubble.core.utils.BubbleDispatchers
import com.example.bubble.statistics.model.StatisticsState
import com.example.bubble.statistics.useCases.GetStatisticDataUseCase
import com.example.bubble.statistics.utls.toStatisticState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class StatisticsViewModel @Inject constructor(
    private val bubbleDispatchers: BubbleDispatchers,
    private val getStatisticDataUseCase: GetStatisticDataUseCase
): ViewModel() {

    internal val state: StateFlow<StatisticsState> = getStatisticDataUseCase()
        .map {
            it.toStatisticState()
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, StatisticsState.DefaultState)

}