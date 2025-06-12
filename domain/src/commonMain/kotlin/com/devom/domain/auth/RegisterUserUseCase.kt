package com.devom.domain.auth

import com.devom.data.repository.user.UserRepository
import com.devom.data.repository.user.UserRepositoryImpl
import com.devom.models.auth.UserRequestResponse

class RegisterUserUseCase {
    private val userRepository: UserRepository = UserRepositoryImpl()

    /**
     * register new User
     */
    suspend operator fun invoke(user: UserRequestResponse) = userRepository.addUser(user)
}