package com.devom.data.repository.other

import com.devom.data.cache.other.OtherLocalDataSourceImpl
import com.devom.data.other.OtherRemoteDataSourceImpl
import com.devom.models.other.City
import com.devom.models.other.Country
import com.devom.models.other.State
import com.devom.utils.cachepolicy.CachePolicy
import com.devom.utils.flow.cacheAwareFlow
import com.devom.utils.network.ResponseResult
import kotlinx.coroutines.flow.Flow

interface OtherRepository {
    suspend fun getAllCountries(cachePolicy: CachePolicy = CachePolicy.CacheAndNetwork): Flow<ResponseResult<List<Country>>>
    suspend fun getAllStates(
        countryCode: String,
        cachePolicy: CachePolicy = CachePolicy.CacheAndNetwork,
    ): Flow<ResponseResult<List<State>>>

    suspend fun getAllCities(
        countryCode: String,
        stateCode: String,
        cachePolicy: CachePolicy = CachePolicy.CacheAndNetwork,
    ): Flow<ResponseResult<List<City>>>
}

class OtherRepositoryImpl : OtherRepository {
    private val otherRemoteDataSource = OtherRemoteDataSourceImpl()
    private val otherLocalDataSource = OtherLocalDataSourceImpl()
    override suspend fun getAllCountries(cachePolicy: CachePolicy): Flow<ResponseResult<List<Country>>> =
        cacheAwareFlow(
            cachePolicy = cachePolicy,
            fetchCache = { otherLocalDataSource.getAllCountries() },
            saveCache = { otherLocalDataSource.saveAllCountries(it) },
            fetchNetwork = { otherRemoteDataSource.getCountryList() })

    override suspend fun getAllStates(
        countryCode: String,
        cachePolicy: CachePolicy,
    ): Flow<ResponseResult<List<State>>> = cacheAwareFlow(
        cachePolicy = cachePolicy,
        fetchCache = { otherLocalDataSource.getAllStates(countryCode) },
        saveCache = { otherLocalDataSource.saveAllStates(countryCode, it) },
        fetchNetwork = { otherRemoteDataSource.getStateList(countryCode) })

    override suspend fun getAllCities(
        countryCode: String,
        stateCode: String,
        cachePolicy: CachePolicy,
    ): Flow<ResponseResult<List<City>>> = cacheAwareFlow(
        cachePolicy = cachePolicy,
        fetchCache = { otherLocalDataSource.getAllCities(countryCode, stateCode) },
        saveCache = { otherLocalDataSource.saveAllCities(countryCode, stateCode, it) },
        fetchNetwork = { otherRemoteDataSource.getCityList(countryCode, stateCode) })
}
