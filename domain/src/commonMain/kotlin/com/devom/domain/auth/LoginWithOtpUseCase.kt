package com.devom.domain.auth

import com.devom.data.repository.user.UserRepository
import com.devom.data.repository.user.UserRepositoryImpl
import com.devom.models.auth.LoginWithOtpRequest

class LoginWithOtpUseCase {
    private val userRepository: UserRepository = UserRepositoryImpl()

    /**
     * login with otp
     */
    suspend operator fun invoke(credentials : LoginWithOtpRequest) = userRepository.loginWithOtp(credentials)}