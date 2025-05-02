package com.devom.user

import com.devom.domain.auth.GenerateOtpUseCase
import com.devom.domain.auth.LoginWithOtpUseCase
import com.devom.domain.auth.RegisterUserUseCase

class User {
    val registerUserUseCase by lazy { RegisterUserUseCase() }
    val loginWithOtpUseCase by lazy { LoginWithOtpUseCase() }
    val generateOtpUseCase by lazy { GenerateOtpUseCase() }
}