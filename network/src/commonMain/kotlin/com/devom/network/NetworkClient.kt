package com.devom.network

import co.touchlab.kermit.Logger
import com.devom.network.models.NetworkClientConfig
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.runBlocking

object NetworkClient {

    var config: NetworkClientConfig = NetworkClientConfig()

    var ktorClient: HttpClient = buildClient()
        private set
        get() {
            if (config.baseUrl.isNullOrBlank()) throw IllegalStateException("Base URL not configured please call configure method")
            return field
        }

    fun configure(configure: NetworkClientConfig.() -> Unit) {
        config = NetworkClientConfig().apply(configure)
        ktorClient = buildClient()
    }

    private fun buildClient(): HttpClient = HttpClient {

        if (config.enableLogging) install(Logging) {
            logger = config.logger
            level = config.logLevel
        }

        install(ContentNegotiation) {
            json(config.jsonConfig)
        }

        install(HttpRequestRetry) {
            maxRetries = 1

            retryIf { request, response ->
                val shouldRetry = (response.status == HttpStatusCode.Unauthorized)
                TokenManager.isTokenExpired = (shouldRetry)
                shouldRetry
            }

            delayMillis { retry -> retry * 1000L }

            modifyRequest { request ->
                if (TokenManager.isTokenExpired) runBlocking {
                    Logger.d("AccessToken Is Expired Refreshing Now")
                    TokenManager.refreshToken()
                    request.headers.remove(HttpHeaders.Authorization)
                    config.mainHeaders.forEach { (key, value) ->
                        request.headers.remove(key)
                        request.headers.append(key, value)
                    }
                    config.addHeaders?.let { addHeaders ->
                       request.headers.appendAll(Headers.build { addHeaders() })
                    }
                }
            }
        }

        install(HttpTimeout) {
            requestTimeoutMillis = config.requestTimeoutMillis
            connectTimeoutMillis = config.connectTimeoutMillis
            socketTimeoutMillis = config.socketTimeoutMillis
        }

        defaultRequest {
            config.mainHeaders.forEach { (key, value) ->
                headers.remove(key)
                headers.append(key, value)
            }

            config.addHeaders?.let { addHeaders ->
                headers.appendAll(Headers.build { addHeaders() })
            }

            config.baseUrl?.let { url(it) }
        }
    }

    fun onLogout(message: String) {
        Logger.d("AccessToken SessionIs Expired Logging Out")
        config.onLogOut(message)
    }
}
