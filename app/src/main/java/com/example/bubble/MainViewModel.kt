package com.example.bubble

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bubble.award.useCase.AddAwardUseCase
import com.example.bubble.award.useCase.UpdateAwardUseCase
import com.example.bubble.award.utils.AwardCodes
import com.example.bubble.core.utils.BubbleDispatchers
import com.example.bubble.data.local.dataStore.DataStoreManager
import com.example.bubble.data.local.database.dbo.AwardEntity
import com.example.bubble.data.local.sharedPref.AwardSharedPref
import com.example.bubble.core.utils.User
import com.example.bubble.home.use_cases.GetUserUnlockedAwardsCountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val updateAwardUseCase: UpdateAwardUseCase,
    private val bubbleDispatchers: BubbleDispatchers,
    private val addAwardUseCase: AddAwardUseCase,
    private val getUserUnlockedAwardsCountUseCase: GetUserUnlockedAwardsCountUseCase,
    private val dataStoreManager: DataStoreManager
): ViewModel() {

    private var _userAwardsCount = MutableStateFlow("")
    internal val userAwardsCount: StateFlow<String> = _userAwardsCount.asStateFlow()

    val user: StateFlow<User> = dataStoreManager
        .getUserData()
        .stateIn(viewModelScope, SharingStarted.Lazily, User())

    fun updateAchiv(code: AwardCodes) {
        viewModelScope.launch(bubbleDispatchers.io) {
            updateAwardUseCase.updateAward(code)
        }
    }

    fun addAchiv() {
        viewModelScope.launch(bubbleDispatchers.io) {
            val awardEntity = AwardEntity(
                name = "name",
                title = "title",
                isUnlocked = false
            )
            addAwardUseCase.invoke(awardEntity)
        }
    }

    internal fun getAwardCount() {
        viewModelScope.launch(bubbleDispatchers.io) {
            _userAwardsCount.value =
                "${getUserUnlockedAwardsCountUseCase()} / ${AwardSharedPref.ALL_AWARDS_COUNT}"
        }
    }
}

sealed interface HeaderState{
    data object Default: HeaderState
}