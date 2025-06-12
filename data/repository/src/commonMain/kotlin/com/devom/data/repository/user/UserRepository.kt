package com.devom.data.repository.user

import com.devom.data.cache.user.UserLocalDataSourceImpl
import com.devom.data.server.auth.UserRemoteDataSourceImpl
import com.devom.models.GenericResponse
import com.devom.models.auth.CreateUserResponse
import com.devom.models.auth.LoginWithOtpRequest
import com.devom.models.auth.UserRequestResponse
import com.devom.utils.cachepolicy.CachePolicy
import com.devom.utils.flow.apiFlow
import com.devom.utils.flow.cacheAwareFlow
import com.devom.utils.network.ResponseResult
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun addUser(user : UserRequestResponse) : Flow<ResponseResult<CreateUserResponse>>
    suspend fun loginWithOtp(credentials : LoginWithOtpRequest) : Flow<ResponseResult<UserRequestResponse>>
    suspend fun generateOtp(body : Map<String , String>) : Flow<ResponseResult<GenericResponse>>
    suspend fun getUser(params : Map<String , Any>) : Flow<ResponseResult<UserRequestResponse>>
    suspend fun updateUserProfile(user : UserRequestResponse, image: ByteArray? = null) : Flow<ResponseResult<UserRequestResponse>>
    suspend fun getUserProfile(cachePolicy: CachePolicy = CachePolicy.CacheAndNetwork) : Flow<ResponseResult<UserRequestResponse>>
}

class UserRepositoryImpl : UserRepository {
    private val remoteDataSource = UserRemoteDataSourceImpl()
    private val localDataSource = UserLocalDataSourceImpl()

    /**
     * add new user
     */
    override suspend fun addUser(user: UserRequestResponse): Flow<ResponseResult<CreateUserResponse>> =
        apiFlow {
            remoteDataSource.signUp(user)
        }

    override suspend fun loginWithOtp(credentials: LoginWithOtpRequest): Flow<ResponseResult<UserRequestResponse>> = apiFlow {
        remoteDataSource.loginWithOtp(credentials)
    }

    override suspend fun generateOtp(body : Map<String , String>): Flow<ResponseResult<GenericResponse>> = apiFlow {
        remoteDataSource.generateOtp(body)
    }

    override suspend fun getUser(params : Map<String , Any>): Flow<ResponseResult<UserRequestResponse>> = apiFlow {
        remoteDataSource.getUser(params)
    }

    override suspend fun updateUserProfile(
        user: UserRequestResponse,
        image: ByteArray?
    ): Flow<ResponseResult<UserRequestResponse>> = apiFlow {
        remoteDataSource.updateUserProfile(user , image)
    }
    override suspend fun getUserProfile(cachePolicy: CachePolicy): Flow<ResponseResult<UserRequestResponse>> =
        cacheAwareFlow(cachePolicy = cachePolicy, fetchCache = {
            localDataSource.getUserProfile()
        }, saveCache = {
            localDataSource.saveUserProfile(it)
        }, fetchNetwork = {
            remoteDataSource.getUserProfile()
        })
}