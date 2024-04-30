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
        private const val FIRST_AWARD_TOKEN = Constants.FIRST_AWARD_TOKEN
        private const val SECOND_AWARD_TOKEN = Constants.SECOND_AWARD_TOKEN
        private const val THIRD_AWARD_TOKEN = Constants.THIRD_AWARD_TOKEN
        private const val FOURTH_AWARD_TOKEN = Constants.FOURTH_AWARD_TOKEN

        private const val BADGE_AWARD = Constants.BADGE_AWARD

        const val ALL_AWARDS_COUNT = 4
    }

    fun getBadgeAward(): Boolean {
        return awardSharedPref.getBoolean(BADGE_AWARD, false)
    }

    fun updateBadgeAward(isVisible: Boolean) {
        editor.putBoolean(BADGE_AWARD, isVisible)
    }

    fun getClicksCount(): Int {
        return awardSharedPref.getInt(FIRST_AWARD_TOKEN, 0)
    }

    fun updateClicksCount(count: Int) {
        val allCounts = getClicksCount()
        editor.putInt(FIRST_AWARD_TOKEN, allCounts + count).commit()
    }

    fun updateFirstAward(isFirst: Boolean) {
        editor.putBoolean(FIRST_AWARD_TOKEN, isFirst).commit()
    }

    fun getSecondAward(): Boolean {
        return awardSharedPref.getBoolean(SECOND_AWARD_TOKEN, false)
    }

    fun updateSecondAward(isProfessor: Boolean) {
        editor.putBoolean(SECOND_AWARD_TOKEN, isProfessor).commit()
    }

    fun getThirdAward(): Boolean {
        return awardSharedPref.getBoolean(THIRD_AWARD_TOKEN, false)
    }

    fun updateThirdAward(isFancy: Boolean) {
        editor.putBoolean(THIRD_AWARD_TOKEN, isFancy).commit()
    }

    fun getFourthAward(): Boolean {
        return awardSharedPref.getBoolean(FOURTH_AWARD_TOKEN, false)
    }

    fun updateFourthAward(isFourth: Boolean) {
        editor.putBoolean(FOURTH_AWARD_TOKEN, isFourth).commit()
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