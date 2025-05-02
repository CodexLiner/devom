package com.devom.models.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginWithOtpRequest(
    @SerialName("mobile_no") val mobileNo: String,
    @SerialName("otp") val otp: String
)
