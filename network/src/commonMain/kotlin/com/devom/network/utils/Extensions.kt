package com.devom.network.utils

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
import kotlinx.serialization.json.JsonPrimitive

inline fun <reified T> HttpResponse.toResponseResult(): Flow<ResponseResult<T>> = flow {
    val responseText = bodyAsText()
    val result = runCatching {
        val parsed = if (status.isSuccess()) NetworkClient.config.jsonConfig.decodeFromString<BaseResponse<T>>(responseText) else NetworkClient.config.jsonConfig.decodeFromString<BaseResponse<Nothing>>(responseText)
        if (status.isSuccess() && parsed.data != null) ResponseResult.Success(parsed.data)
        else if (status.isSuccess() && parsed.data == null) ResponseResult.SuccessNothing
        else ResponseResult.Error(message = parsed.message.takeIf { it.isNotBlank() } ?: "Something went wrong", code = status.value)
    }.getOrElse {
        ResponseResult.Error(message = it.message ?: "Something went wrong", code = status.value)
    }
    emit(result)
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
    val json = NetworkClient.config.jsonConfig
    val encoded = json.encodeToString(this)
    val jsonObject = json.parseToJsonElement(encoded) as? JsonObject

    return jsonObject?.entries
        ?.filter { (_, value) -> value is JsonPrimitive || value is JsonObject }
        ?.associate { (key, value) ->
            key to when (value) {
                is JsonPrimitive -> value.content
                is JsonObject -> value.toString()
                else -> ""
            }
        } ?: emptyMap()
}