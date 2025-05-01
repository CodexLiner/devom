package com.devom

import com.devom.domain.auth.RegisterUserUseCase

object Project {
    val registerUserUseCase by lazy { RegisterUserUseCase() }
}