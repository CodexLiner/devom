package com.devom.data.repository.user

import auth.UserRemoteDataSourceImpl
import com.devom.models.auth.CreateUserRequest
import com.devom.models.auth.CreateUserResponse
import com.devom.utils.network.ResponseResult
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun addUser(user : CreateUserRequest) : Flow<ResponseResult<CreateUserResponse>>
}

class UserRepositoryImpl : UserRepository {
    private val remoteDataSource = UserRemoteDataSourceImpl()

    /**
     * add new user
     */
    override suspend fun addUser(user: CreateUserRequest): Flow<ResponseResult<CreateUserResponse>> = remoteDataSource.signUp(user)


}