package com.example.bubble.data.local.sharedPref

import android.content.Context
import android.content.SharedPreferences
import com.example.bubble.core.utils.Constants
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton
@Singleton
class SettingsSharedPref @Inject constructor(
    @ApplicationContext private val context : Context
){
    private val prefName = Constants.SETTINGS_SHARED_PREF_NAME
    private var settingsSharedPref: SharedPreferences =
        context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
    private var editor: SharedPreferences.Editor = settingsSharedPref.edit()

    companion object{
        private const val TOKEN_AFFIRMATION = Constants.TOKEN_AFFIRMATION
        private const val TOKEN_POP_BUBBLE = Constants.TOKEN_POP_BUBBLE
        private const val TOKEN_SOUND = Constants.TOKEN_SOUND

        private const val DEFAULT_SOUND_SETTING = true
        private const val DEFAULT_AFFIRMATION_SETTING = false

        // По дефолту система сама лопает Bubble
        private const val DEFAULT_POP_BUBBLE_SETTING = false

        private const val DEFAULT_USER_SIGN_IN = false
    }

    fun updateSoundSetting(isSoundOn: Boolean){
        editor.putBoolean(TOKEN_SOUND, isSoundOn).commit()
    }

    fun getSoundSetting(): Boolean {
        return settingsSharedPref.getBoolean(TOKEN_SOUND, DEFAULT_SOUND_SETTING)
    }

    fun updateAffirmationSetting(isOn: Boolean){
        editor.putBoolean(TOKEN_AFFIRMATION, isOn).commit()
    }

    fun getAffirmationSetting(): Boolean {
        return settingsSharedPref.getBoolean(TOKEN_AFFIRMATION, DEFAULT_AFFIRMATION_SETTING)
    }

    fun updatePopBubbleSetting(isUserPopBubble: Boolean){
        editor.putBoolean(TOKEN_POP_BUBBLE, isUserPopBubble).commit()
    }

    fun getPopBubbleSetting(): Boolean {
        return settingsSharedPref.getBoolean(TOKEN_POP_BUBBLE, DEFAULT_POP_BUBBLE_SETTING)
    }
}