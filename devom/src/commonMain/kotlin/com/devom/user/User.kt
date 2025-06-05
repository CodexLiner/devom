package com.devom.user

import com.devom.domain.auth.GenerateOtpUseCase
import com.devom.domain.auth.GetUserProfileUseCase
import com.devom.domain.auth.GetUserUseCase
import com.devom.domain.auth.LoginWithOtpUseCase
import com.devom.domain.auth.RegisterUserUseCase
import com.devom.domain.auth.UpdateUserProfileUseCase

class User {
    val registerUserUseCase by lazy { RegisterUserUseCase() }
    val loginWithOtpUseCase by lazy { LoginWithOtpUseCase() }
    val getUserProfileUseCase by lazy { GetUserProfileUseCase() }
    val generateOtpUseCase by lazy { GenerateOtpUseCase() }
    val getUserUseCase by lazy { GetUserUseCase() }
    val updateUserProfileUseCase by lazy { UpdateUserProfileUseCase() }

}