package com.example.bubble.domain.utils

sealed class DatabaseResource<T>(private val data: T? = null, private val message: String? = null) {
    class Default<T>(data: T? = null, message: String? = null): DatabaseResource<T>()
    class Loading<T>(data: T? = null): DatabaseResource<T>()
    data class Empty<T>(val data: T?, val message: String? = null): DatabaseResource<T>(data)
    data class Success<T>(val data: T?, val message: String? = null): DatabaseResource<T>(data, message)
    data class Error<T>(val data: T? = null, val message: String?): DatabaseResource<T>(data, message)
}