package com.devom.network

import com.devom.network.models.NetworkClientConfig
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json

object NetworkClient {

    private var config: NetworkClientConfig = NetworkClientConfig()

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

        if (config.enableLogging) {
            install(Logging) {
                logger = config.logger
                level = config.logLevel
            }
        }

        install(ContentNegotiation) {
            json(config.jsonConfig)
        }

        install(HttpTimeout) {
            requestTimeoutMillis = config.requestTimeoutMillis
            connectTimeoutMillis = config.connectTimeoutMillis
            socketTimeoutMillis = config.socketTimeoutMillis
        }

        defaultRequest {
            config.defaultHeaders?.let { headersConfig ->
                headersConfig(this.headers)
            }
            config.baseUrl?.let { url(it) }
        }
    }
}
