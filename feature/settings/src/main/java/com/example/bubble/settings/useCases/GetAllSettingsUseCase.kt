package com.example.bubble.settings.useCases

import android.util.Log
import com.example.bubble.core.utils.BubbleDispatchers
import com.example.bubble.data.local.sharedPref.SettingsSharedPref
import com.example.bubble.domain.model.Settings
import com.example.bubble.domain.model.UserSettings
import com.example.bubble.settings.model.SettingsState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetAllSettingsUseCase @Inject constructor(
    private val settingsSharedPref: SettingsSharedPref,
    private val bubbleDispatchers: BubbleDispatchers
){
    operator fun invoke(): Flow<SettingsState> {
        return flow {

            emit(SettingsState.IsLoadingState)
            try {
                settingsSharedPref.updateUserName("Miha yo")

                val userSettings = UserSettings(
                    name = settingsSharedPref.getUserName(),
                    avatar = settingsSharedPref.getAvatar()
                )

                val functionalSettings = Settings(
                    userSettings = userSettings,
                    isAffirmationOn = settingsSharedPref.getAffirmationSetting(),
                    isUserPopBubble = settingsSharedPref.getPopBubbleSetting(),
                    isSoundOn = settingsSharedPref.getSoundSetting()
                )
                emit(
                    SettingsState.LoadedState(
                    settings = functionalSettings
                ))
            }catch (e: Exception){
                emit(SettingsState.ErrorState(message = e.localizedMessage.toString()))
                e.localizedMessage?.let { Log.d("checkExceptionsMFFFF", it) }
            }
        }.flowOn(bubbleDispatchers.main)
    }
}