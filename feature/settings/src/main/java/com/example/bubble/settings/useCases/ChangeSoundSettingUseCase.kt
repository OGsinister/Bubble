package com.example.bubble.settings.useCases

import com.example.bubble.data.local.sharedPref.SettingsSharedPref
import javax.inject.Inject

class ChangeSoundSettingUseCase @Inject constructor(
    private val settingsSharedPref: SettingsSharedPref
) {
    operator fun invoke(isSoundOn: Boolean){
        settingsSharedPref.updateSoundSetting(isSoundOn = isSoundOn)
    }
}