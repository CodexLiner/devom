package com.devom.domain.auth

import com.devom.data.repository.user.UserRepository
import com.devom.data.repository.user.UserRepositoryImpl

class GenerateOtpUseCase {
    private val userRepository: UserRepository = UserRepositoryImpl()

    /**
     * generate otp
     */
    suspend operator fun invoke(mobileNo: String) = userRepository.generateOtp(
        mapOf("mobile_no" to mobileNo)
    )
}