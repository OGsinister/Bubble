package com.example.bubble.data.utils

sealed class DatabaseResource<E>(internal val data: E? = null) {
    class Default<E>(): DatabaseResource<E>()
    class Loading<E>(): DatabaseResource<E>()
    data class Empty<E>(val emptyData: E? = null, val message: String): DatabaseResource<E>(emptyData)
    data class LoadedData<E>(val loadedData: E, val message: String? = null): DatabaseResource<E>(loadedData)
    data class Error<E>(val errorData: E? = null, val message: String?): DatabaseResource<E>(errorData)
}