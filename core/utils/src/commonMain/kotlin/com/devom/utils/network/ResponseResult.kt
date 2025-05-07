package com.devom.utils.network

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

