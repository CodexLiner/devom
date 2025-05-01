package auth

import com.devom.models.auth.UserRequest
import com.devom.models.auth.UserResponse
import com.devom.utils.network.ResponseResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface UserRemoteDataSource {
    fun signUp(user: UserRequest): Flow<ResponseResult<UserResponse>>
}

class UserRemoteDataSourceImpl : UserRemoteDataSource {
    override fun signUp(user: UserRequest): Flow<ResponseResult<UserResponse>> = flow {
        emit(ResponseResult.Success(UserResponse(email = "success")))
    }
}