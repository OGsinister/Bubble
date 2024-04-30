package com.example.bubble.settings

import android.graphics.Bitmap
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bubble.core.utils.BubbleDispatchers
import com.example.bubble.data.local.dataStore.DataStoreManager
import com.example.bubble.core.utils.User
import com.example.bubble.data.local.sharedPref.AwardSharedPref
import com.example.bubble.settings.model.SettingsEvent
import com.example.bubble.settings.model.SettingsState
import com.example.bubble.settings.useCases.ChangeAffirmationSettingUseCase
import com.example.bubble.settings.useCases.ChangeBubblePopSettingUseCase
import com.example.bubble.settings.useCases.ChangeSoundSettingUseCase
import com.example.bubble.settings.useCases.GetAllSettingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val getAllSettingsUseCase: GetAllSettingsUseCase,
    private val changeAffirmationSettingUseCase: ChangeAffirmationSettingUseCase,
    private val changeBubblePopSettingUseCase: ChangeBubblePopSettingUseCase,
    private val changeSoundSettingUseCase: ChangeSoundSettingUseCase,
    private val awardSharedPref: AwardSharedPref,
    private val dataStoreManager: DataStoreManager,
    private val bubbleDispatchers: BubbleDispatchers
): ViewModel() {

    internal val state: StateFlow<SettingsState> = getAllSettingsUseCase()
        .stateIn(viewModelScope, SharingStarted.Lazily, SettingsState.DefaultState)

    internal val isOpenUserNameDialog = mutableStateOf(false)

    internal val user: StateFlow<User> = dataStoreManager
        .getUserData()
        .stateIn(viewModelScope, SharingStarted.Lazily, User())

    internal val selectedImage = mutableStateOf<Bitmap?>(user.value.image)

    fun event(event: SettingsEvent){
        if (state.value is SettingsState.LoadedState){
            when(event){
                is SettingsEvent.ChangeUserName -> {
                    if (event.name.isNotEmpty()){
                        viewModelScope.launch(bubbleDispatchers.io) {
                            dataStoreManager.updateUserData(
                                user = user.value.copy(
                                    name = event.name
                                )
                            )
                        }
                        //changeUserNameUseCase(name = event.name)
                    }else{
                        // show Error
                    }
                }

                is SettingsEvent.ChangeAffirmationSetting -> {
                    changeAffirmationSettingUseCase(isOn = event.isAffirmationOn)
                }

                is SettingsEvent.ChangeBubblePopSetting -> {
                    changeBubblePopSettingUseCase(isUserPopBubble = event.isUserPopBubble)
                }

                is SettingsEvent.ChangeSoundSetting -> {
                    changeSoundSettingUseCase(isSoundOn = event.isSoundOn)
                }

                is SettingsEvent.ChangeUserAvatar -> {
                    viewModelScope.launch(bubbleDispatchers.io) {
                        dataStoreManager.updateUserData(
                            user = user.value.copy(
                                image = event.avatar
                            )
                        )

                        //dataStoreManager.updateImage(event.avatar)
                    }
                }
            }
        }
    }

    internal fun changeDialog(newValue: Boolean){
        isOpenUserNameDialog.value = newValue
    }

    internal fun updateThirdAward() {
        awardSharedPref.updateThirdAward(true)
    }
}