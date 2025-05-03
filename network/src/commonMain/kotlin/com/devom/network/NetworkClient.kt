package com.devom.network

import co.touchlab.kermit.Logger
import com.devom.network.models.NetworkClientConfig
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.plugin
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.sha1
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
                    config.defaultHeaders?.let { headersConfig ->
                        Logger.d("AccessToken Updated Please Update Headers With New Token")
                        headersConfig(request.headers)
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
            config.defaultHeaders?.invoke(this.headers)
            config.baseUrl?.let { url(it) }
        }
    }

    fun onLogout(message: String) {
        Logger.d("AccessToken SessionIs Expired Logging Out")
        config.onLogOut(message)
    }

    fun setAccessToken(access: String, refresh: String) {
        TokenManager.setTokens(
            access = access,
            refresh = refresh
        )
    }
}
