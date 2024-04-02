package com.example.bubble.settings.useCases

import com.example.bubble.data.local.sharedPref.SettingsSharedPref
import javax.inject.Inject

class ChangeAffirmationSettingUseCase @Inject constructor(
    private val settingsSharedPref: SettingsSharedPref
) {
    operator fun invoke(isOn: Boolean){
        settingsSharedPref.updateAffirmationSetting(isOn)
    }
}