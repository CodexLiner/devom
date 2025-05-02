package com.devom.network.utils

import com.devom.network.models.BaseResponse
import com.devom.utils.network.ResponseResult
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json

inline fun <reified T> HttpResponse.toResponseResult(): Flow<ResponseResult<T>> = flow {
    val responseText = bodyAsText()
    val parsed = Json.decodeFromString<BaseResponse<T>>(responseText)
    emit(
        when {
            status.isSuccess() && parsed.data != null -> ResponseResult.Success(parsed.data)
            else -> {
                ResponseResult.Error(
                    message = parsed.message,
                    code = status.value
                )
            }
        }
    )
}
