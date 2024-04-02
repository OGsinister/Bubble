package com.example.bubble.domain.model

data class User(
    val id: Int? = 0,
    val name: String = "John Doe",
    val image: Int? = null,
    var isSignIn: Boolean = false
)