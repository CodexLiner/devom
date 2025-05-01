package com.devom.data.repository.user

import com.devom.models.auth.UserRequest

interface UserRepository {
    suspend fun addUser(user : UserRequest) : Boolean
}

class UserRepositoryImpl : UserRepository {
    override suspend fun addUser(user: UserRequest): Boolean {
      return true
    }
}