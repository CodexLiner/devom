package com.devom.network.models

import com.devom.network.AUTHORIZATION_HEADER_PREFIX
import com.devom.network.TokenManager
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.http.ContentType
import io.ktor.http.HeadersBuilder
import io.ktor.http.HttpHeaders
import io.ktor.http.append
import kotlinx.serialization.json.Json

class NetworkClientConfig {
    var enableLogging: Boolean = true
    var logger: Logger = Logger.SIMPLE
    var logLevel: LogLevel = LogLevel.ALL

    var requestTimeoutMillis: Long = 30_000
    var connectTimeoutMillis: Long = 15_000
    var socketTimeoutMillis: Long = 30_000

    var jsonConfig: Json = Json {
        prettyPrint = false
        isLenient = true
        ignoreUnknownKeys = true
        encodeDefaults = true
        explicitNulls = false
    }

    fun setTokens(access: String, refresh: String) {
        TokenManager.setTokens(
            access = access,
            refresh = refresh
        )
    }

    val onLogOut : (String) -> Unit = {}

    var defaultHeaders: (HeadersBuilder.() -> Unit)? = {
        append(HttpHeaders.ContentType, ContentType.Application.Json)
        append(HttpHeaders.Accept, ContentType.Application.Json)
        append(HttpHeaders.Authorization, "$AUTHORIZATION_HEADER_PREFIX${TokenManager.getAccessToken()}")
    }

    var baseUrl: String? = null
    var accessTokenEndpoint: String? = "/admin/getaccesstoken"
}
