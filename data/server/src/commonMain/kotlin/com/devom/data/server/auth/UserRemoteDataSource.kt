package com.devom.data.server.auth

import com.devom.data.server.endpoints.AuthEndpoints
import com.devom.models.GenericResponse
import com.devom.models.auth.CreateUserRequest
import com.devom.models.auth.CreateUserResponse
import com.devom.models.auth.LoginWithOtpRequest
import com.devom.models.auth.UserResponse
import com.devom.network.NetworkClient
import com.devom.network.utils.setParams
import com.devom.network.utils.toResponseResult
import com.devom.utils.network.ResponseResult
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.coroutines.flow.Flow

interface UserRemoteDataSource {
   suspend fun signUp(user: CreateUserRequest): Flow<ResponseResult<CreateUserResponse>>
   suspend fun loginWithOtp(credentials : LoginWithOtpRequest) : Flow<ResponseResult<UserResponse>>
   suspend fun generateOtp(body : Map<String , String>) : Flow<ResponseResult<GenericResponse>>
   suspend fun getUser(params : Map<String , Any>) : Flow<ResponseResult<UserResponse>>
   suspend fun getUserProfile() : Flow<ResponseResult<UserResponse>>
}

class UserRemoteDataSourceImpl : UserRemoteDataSource {
    private val ktorClient = NetworkClient.ktorClient

    /**
     * @param user
     * @return Flow<ResponseResult<CreateUserResponse>>
     */
    override suspend fun signUp(user: CreateUserRequest): Flow<ResponseResult<CreateUserResponse>> =
        ktorClient.post(AuthEndpoints.SignUp.path) { setBody(user) }.toResponseResult()

    /**
     * @param credentials
     * @return Flow<ResponseResult<UserResponse>>
     */
    override suspend fun loginWithOtp(credentials: LoginWithOtpRequest): Flow<ResponseResult<UserResponse>> =
        ktorClient.post(AuthEndpoints.LoginWithOtp.path) { setBody(credentials) }.toResponseResult()

    /**
     * @param body
     * @return Flow<ResponseResult<String>>
     */
    override suspend fun generateOtp(body : Map<String , String>): Flow<ResponseResult<GenericResponse>> =
        ktorClient.post(AuthEndpoints.GenerateOtp.path) { setBody(body) }.toResponseResult()

    override suspend fun getUser(params : Map<String , Any>): Flow<ResponseResult<UserResponse>> =
        ktorClient.get(AuthEndpoints.GetUser.path) {
            setParams(params)
        }.toResponseResult()

    override suspend fun getUserProfile(): Flow<ResponseResult<UserResponse>> =
        ktorClient.get(AuthEndpoints.GetUserProfile.path).toResponseResult()
}