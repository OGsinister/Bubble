package com.example.bubble.settings.useCases

import com.example.bubble.data.local.sharedPref.SettingsSharedPref
import javax.inject.Inject

class ChangeAvatarUseCase @Inject constructor(
    private val sharedPref: SettingsSharedPref
) {
    operator fun invoke(avatar: Int){
        sharedPref.updateAvatar(avatar)
    }
}