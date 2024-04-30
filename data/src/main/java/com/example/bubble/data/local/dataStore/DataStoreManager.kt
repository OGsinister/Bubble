package com.example.bubble.data.local.dataStore

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.util.Base64
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.bubble.core.utils.User
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore(name = "data_store")

class DataStoreManager @Inject constructor(
    @ApplicationContext val context: Context
){
    suspend fun updateUserData(user: User){
        context.dataStore.edit { preference ->
            user.name.let {
                preference[stringPreferencesKey("user_name")] = it
            }

            user.image.let {
                val baos = ByteArrayOutputStream()
                it?.compress(Bitmap.CompressFormat.JPEG, 100, baos)
                val b = baos.toByteArray()

                preference[stringPreferencesKey("user_avatar")] = Base64.encodeToString(b, Base64.DEFAULT)
            }
        }
    }

    fun getUserData(): Flow<User> {
        return context.dataStore.data
            .map { preference ->
                val pref: String = preference[stringPreferencesKey("user_avatar")] ?: ""

                val decoder = Base64.decode(
                    pref,
                    Base64.DEFAULT
                )

               val bitmap: Bitmap? = BitmapFactory.decodeByteArray(decoder, 0, decoder?.size!!)

                return@map User(
                    name = preference[stringPreferencesKey("user_name")] ?: "John Doe",
                    image = bitmap
                )
        }
    }

    suspend fun updateAwards(value: Int) {
        context.dataStore.edit { preference ->
            preference[intPreferencesKey("awardCount")] = value
        }
    }

    fun getAwardsCount(): Flow<Int> {
        return context.dataStore.data
            .map { preference ->
                val pref = preference[intPreferencesKey("awardCount")] ?: 0

                return@map pref
            }
    }

    suspend fun updateBadgeCount(value: Boolean) {
        context.dataStore.edit { preference ->
            preference[booleanPreferencesKey("badge")] = value
        }
    }

    fun getBadgeCount(): Flow<Boolean> {
        return context.dataStore.data
            .map { preference ->
                val pref = preference[booleanPreferencesKey("badge")] ?: false

                return@map pref
            }
    }
}