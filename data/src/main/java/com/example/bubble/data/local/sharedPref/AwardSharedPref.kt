package com.example.bubble.data.local.sharedPref

import android.content.Context
import android.content.SharedPreferences
import com.example.bubble.core.utils.Constants
import com.example.bubble.data.local.database.dbo.AwardEntity
import com.google.gson.Gson
import javax.inject.Inject
import javax.inject.Singleton
import dagger.hilt.android.qualifiers.ApplicationContext

@Singleton
class AwardSharedPref @Inject constructor(
    @ApplicationContext private val context : Context
){
    private val prefName = Constants.AWARD_SHARED_PREF_NAME
    private var awardSharedPref: SharedPreferences =
        context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
    private var editor: SharedPreferences.Editor = awardSharedPref.edit()

    companion object {
        private const val TOKEN = Constants.AWARD_SHARED_PREF_TOKEN

        const val ALL_AWARDS_COUNT = 5
    }

    fun updateAward(awardEntity: List<AwardEntity>) {
        val jsonObjectAward = Gson().toJson(awardEntity)
        editor.putString(TOKEN, jsonObjectAward).commit()
    }

    fun getAward(): List<AwardEntity> {
        val jsonObjectAward = awardSharedPref.getString(TOKEN, "")
        return Gson().fromJson(jsonObjectAward, Array<AwardEntity>::class.java).asList()
    }
}