package com.example.bubble.settings

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bubble.core.utils.BubbleDispatchers
import com.example.bubble.data.local.sharedPref.SettingsSharedPref
import com.example.bubble.domain.model.User
import com.example.bubble.settings.model.SettingsEvent
import com.example.bubble.settings.model.SettingsState
import com.example.bubble.settings.useCases.ChangeAffirmationSettingUseCase
import com.example.bubble.settings.useCases.ChangeAvatarUseCase
import com.example.bubble.settings.useCases.ChangeBubblePopSettingUseCase
import com.example.bubble.settings.useCases.ChangeSoundSettingUseCase
import com.example.bubble.settings.useCases.ChangeUserNameUseCase
import com.example.bubble.settings.useCases.GetAllSettingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val getAllSettingsUseCase: GetAllSettingsUseCase,
    private val changeUserNameUseCase: ChangeUserNameUseCase,
    private val changeAffirmationSettingUseCase: ChangeAffirmationSettingUseCase,
    private val changeBubblePopSettingUseCase: ChangeBubblePopSettingUseCase,
    private val changeSoundSettingUseCase: ChangeSoundSettingUseCase,
    private val changeAvatarUseCase: ChangeAvatarUseCase,
    private val settingsSharedPref: SettingsSharedPref,
    private val bubbleDispatchers: BubbleDispatchers
): ViewModel() {

    internal val state: StateFlow<SettingsState> = getAllSettingsUseCase()
        .stateIn(viewModelScope, SharingStarted.Lazily, SettingsState.DefaultState)

    internal val isOpenUserNameDialog = mutableStateOf(false)

    private var _user = MutableStateFlow(User(name = settingsSharedPref.getUserName()))
    internal val user: StateFlow<User> = _user.asStateFlow()

    fun event(event: SettingsEvent){
        if (state.value is SettingsState.LoadedState){
            when(event){
                is SettingsEvent.ChangeUserName -> {
                    changeUserNameUseCase(name = event.name)
                }

                is SettingsEvent.ChangeAffirmationSetting -> {
                    changeAffirmationSettingUseCase(isOn = event.isAffirmationOn)
                }

                is SettingsEvent.ChangeBubblePopSetting -> {
                    changeBubblePopSettingUseCase(isUserPopBubble = event.isUserPopBubble)
                }

                is SettingsEvent.ChangeUserAvatar -> {
                    changeAvatarUseCase(avatar = event.avatar)
                }

                is SettingsEvent.ChangeSoundSetting -> {
                    changeSoundSettingUseCase(isSoundOn = event.isSoundOn)
                }
            }
        }
    }

    fun getUserName() {
        _user.value = _user.value.copy(
            name = settingsSharedPref.getUserName()
        )
        //return settingsSharedPref.getUserName()
    }

    internal fun updateUserName(newName: String){
        viewModelScope.launch {
            withContext(bubbleDispatchers.main){
                settingsSharedPref.updateUserName(newName)
            }
        }
        getUserName()
    }
}


