package com.erbe.nowinandroid.core.common.state

import android.util.Log
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

fun <T> DataState<T>.process(
    onLoading: () -> Unit,
    onError: (exception: Throwable?) -> Unit,
    onSuccess: (data: T) -> Unit
) {
    when (this) {
        is DataState.Loading -> {
            Log.d("TAG", "Loading")
            onLoading()
        }
        is DataState.Error -> {
            Log.d("TAG", exception.toString())
            exception?.printStackTrace()
            onError(exception)
        }
        is DataState.Success -> {
            Log.d("TAG", data.toString())
            onSuccess(data)
        }
    }
}