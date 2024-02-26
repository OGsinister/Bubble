package com.example.bubble.data.local

import android.content.Context
import android.content.SharedPreferences
import com.example.bubble.domain.utils.Constants

class WaterSharedPref(
    private val context: Context
) {
    private val prefName = Constants.waterSharedPrefName
    private var waterSharedPref: SharedPreferences =
        context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
    private var editor: SharedPreferences.Editor = waterSharedPref.edit()

    companion object{
        private const val TOKEN = Constants.waterSharedPrefToken
    }

    public fun updateWater(value: Float?){
        val currentValue = getWater()
        value?.let {
            editor.putFloat(TOKEN, it + currentValue).commit()
        }
    }

    public fun getWater(): Float{
        return waterSharedPref.getFloat(TOKEN, 0.0f)
    }
}