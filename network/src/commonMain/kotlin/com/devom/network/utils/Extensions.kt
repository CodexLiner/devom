package com.devom.network.utils

import com.devom.network.models.BaseResponse
import com.devom.utils.network.ResponseResult
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

inline fun <reified T> HttpResponse.toResponseResult(): Flow<ResponseResult<T>> = flow {
    emit(
        when {
            status.isSuccess() -> {
                val body = body<BaseResponse<T>>()
                ResponseResult.Success(body.data)
            }

            else -> {
                val body = body<BaseResponse<T>>()
                ResponseResult.Error(message = body.message, code = status.value)
            }
        }
    )
}