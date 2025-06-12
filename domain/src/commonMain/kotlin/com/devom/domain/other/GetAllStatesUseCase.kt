package com.devom.domain.other

import com.devom.data.repository.other.OtherRepositoryImpl
import com.devom.utils.cachepolicy.CachePolicy

class GetAllStatesUseCase {
    private val repository = OtherRepositoryImpl()
    suspend operator fun invoke(countryCode: String , cachePolicy: CachePolicy = CachePolicy.CacheAndNetwork) = repository.getAllStates(countryCode , cachePolicy)

}