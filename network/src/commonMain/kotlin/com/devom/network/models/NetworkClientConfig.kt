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
        coerceInputValues = true
        isLenient = true
        encodeDefaults = true
        ignoreUnknownKeys = true
    }

    fun setTokens(access: String, refresh: String) {
        TokenManager.setTokens(
            access = access,
            refresh = refresh
        )
    }

    var onLogOut : (String) -> Unit = {}

    internal var addHeaders: (HeadersBuilder.() -> Unit)? = null

    fun getMainHeaders(): Map<String, String> {
        return HeadersBuilder().apply {
            append(HttpHeaders.ContentType, ContentType.Application.Json)
            append(HttpHeaders.Accept, ContentType.Application.Json)
            append(HttpHeaders.Authorization, "$AUTHORIZATION_HEADER_PREFIX${TokenManager.getAccessToken()}")
        }.build().entries().associate { (key, values) -> key to values.last() }
    }



    fun addHeaders(block: HeadersBuilder.() -> Unit) {
        addHeaders = block
    }

    var baseUrl: String? = null
    var accessTokenEndpoint: String? = "/admin/getaccesstoken"
}
