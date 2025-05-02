package com.devom.user

import com.devom.domain.auth.RegisterUserUseCase

class User {
    val registerUserUseCase by lazy { RegisterUserUseCase() }
    
}