package com.devom.network.models

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    val status: Boolean = false,
    val message: String = "",
    val error: String? = null,
    val data: T
)