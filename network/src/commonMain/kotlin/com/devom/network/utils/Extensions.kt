package com.devom.network.utils

import co.touchlab.kermit.Logger
import com.devom.network.NetworkClient
import com.devom.network.models.BaseResponse
import com.devom.utils.network.ResponseResult
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.JsonObject

inline fun <reified T> HttpResponse.toResponseResult(): Flow<ResponseResult<T>> = flow {
    val responseText = bodyAsText()
    Logger.d("DataResponseText $responseText")
    val parsed =  NetworkClient.config.jsonConfig.decodeFromString<BaseResponse<T>>(responseText)
    emit(
        when {
            status.isSuccess() && parsed.data != null -> ResponseResult.Success(parsed.data)
            else -> {
                ResponseResult.Error(
                    message = if (parsed.message.isBlank()) "Something went wrong" else parsed.message,
                    code = status.value
                )
            }
        }
    )
}


inline fun <reified T> HttpRequestBuilder.setParams(params: T) {
    val paramMap: Map<String, String> = when (params) {
        is Map<*, *> -> params.entries.associate { it.key.toString() to it.value.toString() }
        else -> params.toMap()
    }
    paramMap.forEach { (key, value) ->
        url.parameters.append(key, value)
    }

}

inline fun <reified T> T.toMap(): Map<String, String> {
    val encodeJson = NetworkClient.config.jsonConfig.encodeToString(this)
    val jsonElement = (NetworkClient.config.jsonConfig.parseToJsonElement(encodeJson) as? JsonObject)
    return jsonElement?.entries?.associate { it.key to it.value.toString() } ?: emptyMap()
}
