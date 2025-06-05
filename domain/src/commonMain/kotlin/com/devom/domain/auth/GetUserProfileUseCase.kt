package com.devom.domain.auth

import com.devom.data.repository.user.UserRepository
import com.devom.data.repository.user.UserRepositoryImpl
import com.devom.utils.cachepolicy.CachePolicy

class GetUserProfileUseCase {
    private val repository : UserRepository = UserRepositoryImpl()
    suspend operator fun invoke(cachePolicy: CachePolicy = CachePolicy.CacheAndNetwork) = repository.getUserProfile(cachePolicy)
}