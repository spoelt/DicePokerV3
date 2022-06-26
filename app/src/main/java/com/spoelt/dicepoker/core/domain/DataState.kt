package com.spoelt.dicepoker.core.domain

/**
 * This is a sealed class that represents three states that occur during data requests:
 * loading, success or error.
 */
sealed class DataState<out T> {
    data class Success<out T>(val data: T) : DataState<T>()
    data class Error(val error: Throwable) : DataState<Nothing>()
    object Loading : DataState<Nothing>()
}
