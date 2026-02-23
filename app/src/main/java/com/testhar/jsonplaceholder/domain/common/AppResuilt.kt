package com.testhar.jsonplaceholder.domain.common

sealed interface AppResult<out T> {
    data class Success<T>(val data: T) : AppResult<T>
    data class Error(
        val message: String,
        val throwable: Throwable? = null
    ) : AppResult<Nothing>
}