package com.devom.domain.other

import com.devom.data.repository.other.OtherRepositoryImpl
import com.devom.utils.cachepolicy.CachePolicy

class GetAllCitiesUseCase {
    private val repository = OtherRepositoryImpl()
    suspend operator fun invoke(countryCode: String, stateCode: String , cachePolicy: CachePolicy = CachePolicy.CacheAndNetwork) = repository.getAllCities(countryCode, stateCode, cachePolicy)
}