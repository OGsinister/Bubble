package com.example.bubble.award

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bubble.award.model.AwardState
import com.example.bubble.award.useCase.GetAllAwardsUseCase
import com.example.bubble.award.utils.toAwardState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class AwardViewModel @Inject constructor(
    private val getAllAwardsUseCase: GetAllAwardsUseCase
): ViewModel() {

    internal val state: StateFlow<AwardState> = getAllAwardsUseCase()
        .map {
            it.toAwardState()
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, AwardState.DefaultState)

}

