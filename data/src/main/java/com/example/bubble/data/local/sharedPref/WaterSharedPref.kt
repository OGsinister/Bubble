package com.example.bubble.data.local.sharedPref

import android.content.Context
import android.content.SharedPreferences
import com.example.bubble.data.repository.WaterRepository
import com.example.bubble.domain.utils.Constants
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class WaterSharedPref @Inject constructor(
    @ApplicationContext private val context: Context
): WaterRepository {
    private val prefName = Constants.WATER_SHARED_PREF_NAME

    private var waterSharedPref: SharedPreferences =
        context.getSharedPreferences(prefName, Context.MODE_PRIVATE)

    private var editor: SharedPreferences.Editor = waterSharedPref.edit()

    companion object{
        private const val TOKEN = Constants.WATER_SHARED_PREF_TOKEN
    }

    override fun updateBubbleCount(newBubble: Int) {
        val currentBubbleCount = getBubbleCount()
        editor.putInt(TOKEN, currentBubbleCount + newBubble).commit()
    }

    override fun getBubbleCount(): Int {
        return waterSharedPref.getInt(TOKEN, 0)
    }
}