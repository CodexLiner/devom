package com.devom.network.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class BaseResponse<T>(
    val status: Boolean = false,
    val message: String = "",
    val error: JsonElement? = null,
    val data: T? = null,
)