package com.devom.utils.network

sealed class ResponseResult<out T> {
    data class Success<T>(val data: T) : ResponseResult<T>()
    data class Error(val message: String, val code: Int) : ResponseResult<Nothing>()
    object Loading : ResponseResult<Nothing>()
    object NetworkError : ResponseResult<Nothing>()
}


fun <T> ResponseResult<T>.withSuccess(block: () -> Unit) {
    if (this is ResponseResult.Success) {
        block()
    }
}

fun <T> ResponseResult<T>.withError(block: () -> Unit) {
    if (this is ResponseResult.Error) {
        block()
    }
}

fun <T> ResponseResult<T>.withLoading(block: () -> Unit) {
    if (this is ResponseResult.Loading) {
        block()
    }
}