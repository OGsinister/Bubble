package com.example.bubble.data.local.sharedPref

import android.content.Context
import android.content.SharedPreferences
import com.example.bubble.data.repository.WaterRepository
import com.example.bubble.domain.utils.Constants
import javax.inject.Inject

class WaterSharedPref @Inject constructor(
    private val context: Context
): WaterRepository {
    private val prefName = Constants.WATER_SHARED_PREF_NAME
    private var waterSharedPref: SharedPreferences =
        context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
    private var editor: SharedPreferences.Editor = waterSharedPref.edit()

    companion object{
        private const val TOKEN = Constants.WATER_SHARED_PREF_TOKEN
    }

    override fun updateWater(newValue: Float) {
        val currentValue = getWater()
        editor.putFloat(TOKEN, currentValue + newValue).commit()
    }

    override fun getWater(): Float {
        return waterSharedPref.getFloat(TOKEN, 0.0f)
    }
}