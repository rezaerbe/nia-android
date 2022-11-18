package com.erbe.nowinandroid.core.common.state

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

sealed interface DataState<out T> {
    data class Success<T>(val data: T) : DataState<T>
    data class Error(val exception: Throwable? = null) : DataState<Nothing>
    object Loading : DataState<Nothing>
}

fun <T> Flow<T>.asDataState(): Flow<DataState<T>> {
    return this
        .map<T, DataState<T>> {
            DataState.Success(it)
        }
        .onStart { emit(DataState.Loading) }
        .catch { emit(DataState.Error(it)) }
}