package com.example.bubble.settings.useCases

import com.example.bubble.data.local.sharedPref.SettingsSharedPref
import javax.inject.Inject

class ChangeUserNameUseCase @Inject constructor(
    private val settingsSharedPref: SettingsSharedPref
) {
    operator fun invoke(name: String){
        settingsSharedPref.updateUserName(name)
    }
}