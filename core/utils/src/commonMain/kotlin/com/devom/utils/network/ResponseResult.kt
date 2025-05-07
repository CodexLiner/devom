package com.devom.utils.network

import com.devom.utils.Application

sealed class ResponseResult<out T> {
    data class Success<T>(val data: T) : ResponseResult<T>()
    data class Error(val message: String, val code: Int) : ResponseResult<Nothing>()
    object Loading : ResponseResult<Nothing>()
    object NetworkError : ResponseResult<Nothing>()
}

fun <T> ResponseResult<T>.withSuccess(block: (ResponseResult.Success<T>) -> Unit) {
    if (this is ResponseResult.Success) block(this)
}

fun <T> ResponseResult<T>.withError(block: (ResponseResult.Error) -> Unit) {
    if (this is ResponseResult.Error)block(this)
}

fun <T> ResponseResult<T>.withLoading(block: (ResponseResult.Loading) -> Unit) {
    if (this is ResponseResult.Loading) block(this)
}

fun <T> ResponseResult<T>.doOnNetworkError(block: (ResponseResult.NetworkError) -> Unit) {
    if (this is ResponseResult.NetworkError) block(this)
}

fun <T> ResponseResult<T>.onResult(block: (ResponseResult<T>) -> Unit) {
    when (this) {
        is ResponseResult.Error -> {
            Application.showToast(this.message)
            Application.hide()
        }

        ResponseResult.Loading -> {
            Application.show()
        }

        ResponseResult.NetworkError -> {
            Application.showToast("No Internet Connection")
            Application.hide()
        }

        is ResponseResult.Success<*> -> {
            Application.hide()
            block(this)
        }
    }
}
