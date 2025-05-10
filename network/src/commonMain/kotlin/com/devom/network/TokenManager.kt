package com.devom.network

import com.devom.network.models.BaseResponse
import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.set
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.HttpStatusCode
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

internal object TokenManager {
    @Serializable
    data class AuthTokenData(
        @SerialName("access_token") val accessToken: String,
        @SerialName("refresh_token") val refreshToken: String,
    )
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
    suspend fun refreshToken(): String? = runCatching {
        val response = NetworkClient.ktorClient.post(NetworkClient.config.accessTokenEndpoint.orEmpty()) {
            setBody(mapOf("refresh_token" to refreshToken))
        }

        when (response.status) {
            HttpStatusCode.OK -> {
                val newToken = response.body<BaseResponse<AuthTokenData>>().data
                settings[ACCESS_TOKEN_KEY] = newToken?.accessToken.orEmpty()
                accessToken = newToken?.accessToken.orEmpty()
                settings[REFRESH_TOKEN_KEY] = newToken?.refreshToken.orEmpty()
                refreshToken = newToken?.refreshToken.orEmpty()
                newToken?.accessToken.orEmpty()
            }
            HttpStatusCode.Unauthorized -> {
                logout()
                throw Exception("Session Expired")
            }
            else -> throw Exception("Session Expired")
        }
    }.getOrNull()



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
