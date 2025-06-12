package com.devom.data.server.auth

import com.devom.data.server.endpoints.AuthEndpoints
import com.devom.models.GenericResponse
import com.devom.models.auth.CreateUserRequest
import com.devom.models.auth.CreateUserResponse
import com.devom.models.auth.LoginWithOtpRequest
import com.devom.models.auth.UserResponse
import com.devom.network.NetworkClient
import com.devom.network.utils.setParams
import com.devom.network.utils.toMap
import com.devom.network.utils.toResponseResult
import com.devom.utils.network.ResponseResult
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import kotlinx.coroutines.flow.Flow

interface UserRemoteDataSource {
    suspend fun signUp(user: CreateUserRequest): Flow<ResponseResult<CreateUserResponse>>
    suspend fun loginWithOtp(credentials: LoginWithOtpRequest): Flow<ResponseResult<UserResponse>>
    suspend fun generateOtp(body: Map<String, String>): Flow<ResponseResult<GenericResponse>>
    suspend fun getUser(params: Map<String, Any>): Flow<ResponseResult<UserResponse>>
    suspend fun getUserProfile(): Flow<ResponseResult<UserResponse>>
    suspend fun updateUserProfile(
        user: UserResponse,
        image: ByteArray? = null,
    ): Flow<ResponseResult<UserResponse>>
}

class UserRemoteDataSourceImpl : UserRemoteDataSource {
    private val ktorClient = NetworkClient.ktorClient

    /**
     * @param user
     * @return Flow<ResponseResult<CreateUserResponse>>
     */
    override suspend fun signUp(user: CreateUserRequest): Flow<ResponseResult<CreateUserResponse>> =
        ktorClient.post(AuthEndpoints.SignUp) { setBody(user) }.toResponseResult()

    /**
     * @param credentials
     * @return Flow<ResponseResult<UserResponse>>
     */
    override suspend fun loginWithOtp(credentials: LoginWithOtpRequest): Flow<ResponseResult<UserResponse>> =
        ktorClient.post(AuthEndpoints.LoginWithOtp) { setBody(credentials) }.toResponseResult()

    /**
     * @param body
     * @return Flow<ResponseResult<String>>
     */
    override suspend fun generateOtp(body: Map<String, String>): Flow<ResponseResult<GenericResponse>> =
        ktorClient.post(AuthEndpoints.GenerateOtp) { setBody(body) }.toResponseResult()

    override suspend fun getUser(params: Map<String, Any>): Flow<ResponseResult<UserResponse>> =
        ktorClient.get(AuthEndpoints.GetUser) {
            setParams(params)
        }.toResponseResult()

    override suspend fun getUserProfile(): Flow<ResponseResult<UserResponse>> =
        ktorClient.get(AuthEndpoints.GetUserProfile).toResponseResult()

    override suspend fun updateUserProfile(
        user: UserResponse,
        image: ByteArray?,
    ): Flow<ResponseResult<UserResponse>> =
        ktorClient.put(AuthEndpoints.UpdateUserProfile) {
            setBody(
                MultiPartFormDataContent(
                    formData {
                        user.toMap().entries.map {
                            if (it.value.isNotBlank() && it.value::class != ByteArray::class)
                                append(it.key, it.value)
                        }
                        image?.let {
                            append("file", it, Headers.build {
                                append(HttpHeaders.ContentType, "image/*")
                                append(
                                    HttpHeaders.ContentDisposition,
                                    "filename=\"${user.imageFileName}\""
                                )
                            })
                        }
                    }
                )
            )
        }.toResponseResult()
}