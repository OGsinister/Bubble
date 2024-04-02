package com.example.bubble.settings.useCases

import com.example.bubble.data.local.sharedPref.SettingsSharedPref
import javax.inject.Inject

class ChangeBubblePopSettingUseCase @Inject constructor(
    private val settingsSharedPref: SettingsSharedPref
){
    operator fun invoke(isUserPopBubble: Boolean){
        settingsSharedPref.updatePopBubbleSetting(isUserPopBubble)
    }
}