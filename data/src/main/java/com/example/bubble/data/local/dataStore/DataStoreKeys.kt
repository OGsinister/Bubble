package com.example.bubble.data.local.dataStore

import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey

object DataStoreKeys {
    val FIELD_USER_NAME = stringPreferencesKey("name")
    val FIELD_USER_AVATAR = stringSetPreferencesKey("avatar")
}