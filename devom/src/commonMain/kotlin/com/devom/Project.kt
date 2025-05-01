package com.devom

import com.devom.domain.auth.RegisterUserUseCase
import com.devom.user.User

object Project {
    val user by lazy { User() }
}