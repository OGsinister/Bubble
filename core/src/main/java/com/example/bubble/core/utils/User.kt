package com.example.bubble.core.utils

import android.graphics.Bitmap

data class User(
    var name: String = "",
    val image: Bitmap? = null,
    var isSignIn: Boolean = false
)