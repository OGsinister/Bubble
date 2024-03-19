package com.example.bubble.data.local.sharedPref

import android.content.Context
import android.content.SharedPreferences
import com.example.bubble.core.utils.Constants
import com.example.bubble.data.local.database.dbo.AwardEntity
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
@Singleton
class UserSharedPref @Inject constructor(
    @ApplicationContext private val context : Context
){
    private val prefName = Constants.USER_SHARED_PREF_NAME
    private var userSharedPref: SharedPreferences =
        context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
    private var editor: SharedPreferences.Editor = userSharedPref.edit()

    companion object{
        private const val TOKEN = Constants.USER_SHARED_PREF_TOKEN
    }

    fun updateName(name: String) {
        editor.putString(TOKEN, name).commit()
    }

    fun getName(): String? {
        return userSharedPref.getString(TOKEN, null)
    }
}