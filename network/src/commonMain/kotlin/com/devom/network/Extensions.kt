package com.devom.network

import com.devom.models.auth.UserRequestResponse
import com.devom.network.TokenManager.settings
import com.russhwolf.settings.get


fun getUser() : UserRequestResponse {
    val userString = settings.get<String>(USER).orEmpty()
    return NetworkClient.config.jsonConfig.decodeFromString(userString)
}