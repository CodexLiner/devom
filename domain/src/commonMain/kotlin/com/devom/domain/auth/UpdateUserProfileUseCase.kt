package com.devom.domain.auth

import com.devom.data.repository.user.UserRepository
import com.devom.data.repository.user.UserRepositoryImpl
import com.devom.models.auth.UserRequestResponse

class UpdateUserProfileUseCase {
    private val userRepository: UserRepository = UserRepositoryImpl()
    suspend operator fun invoke(user: UserRequestResponse, image: ByteArray? = null) =
        userRepository.updateUserProfile(user , image)
}