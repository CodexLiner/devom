package com.devom.models

import kotlinx.serialization.Serializable

@Serializable
data class GenericResponse(
    val error: String = "",
    val otp : String = "",
)