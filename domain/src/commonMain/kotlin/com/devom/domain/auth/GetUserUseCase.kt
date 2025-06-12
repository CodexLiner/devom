package com.devom.domain.auth

import com.devom.data.repository.user.UserRepository
import com.devom.data.repository.user.UserRepositoryImpl

class GetUserUseCase {
    private val repository : UserRepository = UserRepositoryImpl()

    /**
     * @param params : Map<String , Any>
     * @return Flow<ResponseResult<UserResponse>>
     */
    suspend operator fun invoke(params : Map<String , Any>) = repository.getUser(params)
}