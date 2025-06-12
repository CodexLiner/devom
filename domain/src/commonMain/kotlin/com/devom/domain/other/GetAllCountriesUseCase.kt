package com.devom.domain.other

import com.devom.data.repository.other.OtherRepositoryImpl
import com.devom.utils.cachepolicy.CachePolicy

class GetAllCountriesUseCase {
    private val repository = OtherRepositoryImpl()
    suspend operator fun invoke(cachePolicy: CachePolicy = CachePolicy.CacheAndNetwork) = repository.getAllCountries(cachePolicy)
}