package com.example.bubble.award

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bubble.award.model.AwardState
import com.example.bubble.award.useCase.GetAllAwardsUseCase
import com.example.bubble.award.utils.toAwardState
import com.example.bubble.data.local.dataStore.DataStoreManager
import com.example.bubble.data.local.sharedPref.AwardSharedPref
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AwardViewModel @Inject constructor(
    private val getAllAwardsUseCase: GetAllAwardsUseCase,
    private val dataStoreManager: DataStoreManager
): ViewModel() {
    internal val state: StateFlow<AwardState> = getAllAwardsUseCase()
        .map {
            it.toAwardState()
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, AwardState.DefaultState)

    private val badge: StateFlow<Boolean> = dataStoreManager
        .getBadgeCount()
        .stateIn(viewModelScope, SharingStarted.Lazily, false)

    internal fun updateBadgeAward() {
        viewModelScope.launch {
            dataStoreManager.updateBadgeCount(!badge.value)
        }
    }
}

