package com.devom

import com.devom.domain.auth.RegisterUserUseCase
import com.devom.pooja.Pooja
import com.devom.user.User

object Project {
    /**
     * contains all the use cases for user and auth related operations
     */
    val user by lazy { User() }

    /**
     * contains all the use cases for pooja related operations
     */
    val pooja by lazy { Pooja() }

}