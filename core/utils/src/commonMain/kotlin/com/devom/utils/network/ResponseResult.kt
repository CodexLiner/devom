package com.devom.utils.network

sealed class ResponseResult<out T> {
    data class Success<T>(val data: T) : ResponseResult<T>()
    data class Error(val message: String , val code : Int) : ResponseResult<Nothing>()
    object Loading : ResponseResult<Nothing>()
    object NetworkError : ResponseResult<Nothing>()
}