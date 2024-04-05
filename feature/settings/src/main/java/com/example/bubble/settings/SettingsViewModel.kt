package com.example.bubble.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bubble.settings.model.SettingsEvent
import com.example.bubble.settings.model.SettingsState
import com.example.bubble.settings.useCases.ChangeAffirmationSettingUseCase
import com.example.bubble.settings.useCases.ChangeAvatarUseCase
import com.example.bubble.settings.useCases.ChangeBubblePopSettingUseCase
import com.example.bubble.settings.useCases.ChangeSoundSettingUseCase
import com.example.bubble.settings.useCases.ChangeUserNameUseCase
import com.example.bubble.settings.useCases.GetAllSettingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val getAllSettingsUseCase: GetAllSettingsUseCase,
    private val changeUserNameUseCase: ChangeUserNameUseCase,
    private val changeAffirmationSettingUseCase: ChangeAffirmationSettingUseCase,
    private val changeBubblePopSettingUseCase: ChangeBubblePopSettingUseCase,
    private val changeSoundSettingUseCase: ChangeSoundSettingUseCase,
    private val changeAvatarUseCase: ChangeAvatarUseCase
): ViewModel() {

    internal val state: StateFlow<SettingsState> = getAllSettingsUseCase()
        .stateIn(viewModelScope, SharingStarted.Lazily, SettingsState.DefaultState)

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
}


