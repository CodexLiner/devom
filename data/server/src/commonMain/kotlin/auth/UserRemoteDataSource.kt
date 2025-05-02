package auth

import com.devom.models.auth.CreateUserRequest
import com.devom.models.auth.CreateUserResponse
import com.devom.models.auth.LoginWithOtpRequest
import com.devom.models.auth.UserResponse
import com.devom.network.NetworkClient
import com.devom.network.utils.toResponseResult
import com.devom.utils.network.ResponseResult
import endpoints.AuthEndpoints
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.coroutines.flow.Flow

interface UserRemoteDataSource {
   suspend fun signUp(user: CreateUserRequest): Flow<ResponseResult<CreateUserResponse>>
   suspend fun loginWithOtp(credentials : LoginWithOtpRequest) : Flow<ResponseResult<UserResponse>>
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
        ktorClient.post { setBody(credentials) }.toResponseResult()


}