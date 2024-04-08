package com.example.bubble.domain.model

data class User(
    var name: String? = null,
    val image: Int? = null,
    var isSignIn: Boolean = false
)