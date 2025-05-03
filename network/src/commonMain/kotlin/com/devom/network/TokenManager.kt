package com.devom.network

import com.devom.network.models.BaseResponse
import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.set
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody

internal object TokenManager {

    val settings: Settings = Settings()
    private var refreshToken: String? = null
    private var accessToken: String? = null
    var isTokenExpired: Boolean = false

    /**
     * initializes tokens from settings
     * @param settings settings to read tokens from
     * @param REFRESH_TOKEN_KEY key to read refresh token from
     * @param ACCESS_TOKEN_KEY key to read access token from
     */
    init {
        refreshToken = settings.get<String>(REFRESH_TOKEN_KEY)
        accessToken = settings.get<String>(ACCESS_TOKEN_KEY)
    }

    /**
     * sets tokens to settings
     * @param access access token
     * @param refresh refresh token
     * @param settings settings to write tokens to
     */
    fun setTokens(access: String, refresh: String) {
        settings[REFRESH_TOKEN_KEY] = refresh
        settings[ACCESS_TOKEN_KEY] = access
        accessToken = access
        refreshToken = refresh
    }

    /**
     * gets access token
     * @return access token
     */
    fun getAccessToken(): String? = accessToken

    /**
     * gets refresh token
     */
    suspend fun refreshToken(): String? {
        return runCatching {
            NetworkClient.ktorClient.post(NetworkClient.config.accessTokenEndpoint.orEmpty()) {
                setBody(
                    mapOf("refresh_token" to refreshToken)
                )
            }.body<BaseResponse<String>>()
        }.onSuccess { response ->
            response.data?.let {
                settings[ACCESS_TOKEN_KEY] = it
                accessToken = it
            } ?: run {
                logout()
            }
        }.getOrNull()?.data.orEmpty()
    }


    /**
    * Logs out the user by clearing tokens and performing any additional logout actions
    */
    fun logout() {
        settings.remove(REFRESH_TOKEN_KEY)
        settings.remove(ACCESS_TOKEN_KEY)
        accessToken = null
        refreshToken = null
        NetworkClient.onLogout("Session Expired")
    }
}
