package com.devom.data.repository.user

import auth.UserRemoteDataSource
import auth.UserRemoteDataSourceImpl
import com.devom.models.auth.UserRequest
import com.devom.models.auth.UserResponse
import com.devom.utils.network.ResponseResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface UserRepository {
    suspend fun addUser(user : UserRequest) : Flow<ResponseResult<UserResponse>>
}

class UserRepositoryImpl : UserRepository {
    private val remoteDataSource = UserRemoteDataSourceImpl()

    /**
     * add new user
     */
    override suspend fun addUser(user: UserRequest): Flow<ResponseResult<UserResponse>> = remoteDataSource.signUp(user)


}