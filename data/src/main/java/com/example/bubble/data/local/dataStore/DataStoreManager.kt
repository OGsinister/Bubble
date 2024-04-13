package com.example.bubble.data.local.dataStore

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.util.Base64
import androidx.annotation.RequiresApi
import androidx.datastore.preferences.core.edit
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

                val decoder = Base64.decode(
                    preference[stringPreferencesKey("user_avatar")],
                    Base64.DEFAULT
                )

                return@map User(
                    name = preference[stringPreferencesKey("user_name")] ?: "John Doe",
                    image = BitmapFactory.decodeByteArray(decoder, 0, decoder.size)
                )
        }
    }
}