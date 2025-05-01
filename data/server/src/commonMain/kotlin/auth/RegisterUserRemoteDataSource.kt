package auth

import com.devom.models.auth.UserRequest

interface RegisterUserRemoteDataSource {
    fun signUp(user: UserRequest): Boolean
}

private class RegisterUserRemoteDataSourceImpl : RegisterUserRemoteDataSource {
    override fun signUp(user: UserRequest): Boolean {
        return true
    }
}