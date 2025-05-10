package com.devom.data.repository.user

import com.devom.data.server.auth.UserRemoteDataSourceImpl
import com.devom.models.GenericResponse
import com.devom.models.auth.CreateUserRequest
import com.devom.models.auth.CreateUserResponse
import com.devom.models.auth.LoginWithOtpRequest
import com.devom.models.auth.UserResponse
import com.devom.utils.flow.apiFlow
import com.devom.utils.network.ResponseResult
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun addUser(user : CreateUserRequest) : Flow<ResponseResult<CreateUserResponse>>
    suspend fun loginWithOtp(credentials : LoginWithOtpRequest) : Flow<ResponseResult<UserResponse>>
    suspend fun generateOtp(body : Map<String , String>) : Flow<ResponseResult<GenericResponse>>
    suspend fun getUser(params : Map<String , Any>) : Flow<ResponseResult<UserResponse>>
}

class UserRepositoryImpl : UserRepository {
    private val remoteDataSource = UserRemoteDataSourceImpl()

    /**
     * add new user
     */
    override suspend fun addUser(user: CreateUserRequest): Flow<ResponseResult<CreateUserResponse>> =
        apiFlow {
            remoteDataSource.signUp(user)
        }

    override suspend fun loginWithOtp(credentials: LoginWithOtpRequest): Flow<ResponseResult<UserResponse>> = apiFlow {
        remoteDataSource.loginWithOtp(credentials)
    }

    override suspend fun generateOtp(body : Map<String , String>): Flow<ResponseResult<GenericResponse>> = apiFlow {
        remoteDataSource.generateOtp(body)
    }

    override suspend fun getUser(params : Map<String , Any>): Flow<ResponseResult<UserResponse>> = apiFlow {
        remoteDataSource.getUser(params)
    }

}