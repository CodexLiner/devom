package com.devom.models.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateUserResponse(
    @SerialName("client_id") val clientId: Int = 0,

    @SerialName("client_name") val clientName: String = "",

    @SerialName("client_secret") val clientSecret: String = "",

    @SerialName("redirect_uri") val redirectUri: String = "",

    @SerialName("client_key") val clientKey: String = "",

    @SerialName("uuid") val uuid: String = "",

    @SerialName("encrypted_key") val encryptedKey: String = "",

    @SerialName("iv") val iv: String = "",

    @SerialName("created_at") val createdAt: String = "",

    @SerialName("updated_at") val updatedAt: String = "",
)

