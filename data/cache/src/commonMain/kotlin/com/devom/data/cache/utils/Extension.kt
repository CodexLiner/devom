package com.devom.data.cache.utils

import com.devom.utils.network.ResponseResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json

var jsonConfig: Json = Json {
    prettyPrint = false
    isLenient = true
    ignoreUnknownKeys = true
    encodeDefaults = true
    explicitNulls = false
}

inline fun <reified T> String?.toCacheResult(): Flow<ResponseResult<T>> = flow {
    this@toCacheResult?.let {
        val jsonData = jsonConfig.decodeFromString<T>(this@toCacheResult)
        emit(ResponseResult.Success(jsonData))
    } ?: emit(ResponseResult.Error("No Cache found", 404))
}
