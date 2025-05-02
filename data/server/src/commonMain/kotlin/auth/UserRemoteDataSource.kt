package auth

import com.devom.models.auth.CreateUserRequest
import com.devom.models.auth.CreateUserResponse
import com.devom.utils.network.ResponseResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface UserRemoteDataSource {
    fun signUp(user: CreateUserRequest): Flow<ResponseResult<CreateUserResponse>>
}

class UserRemoteDataSourceImpl : UserRemoteDataSource {
    override fun signUp(user: CreateUserRequest): Flow<ResponseResult<CreateUserResponse>> = flow {
        emit(ResponseResult.Success(CreateUserResponse(clientId = 100)))
    }
}