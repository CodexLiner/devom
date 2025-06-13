package com.devom.data.cache.other

import com.devom.data.cache.CacheHelper
import com.devom.data.cache.utils.jsonConfig
import com.devom.data.cache.utils.toCacheResult
import com.devom.models.other.City
import com.devom.models.other.Country
import com.devom.models.other.State
import com.devom.utils.network.ResponseResult
import kotlinx.coroutines.flow.Flow

interface OtherLocalDataSource {
    suspend fun getAllCountries(): Flow<ResponseResult<List<Country>>>
    suspend fun saveAllCountries(data: List<Country>)
    suspend fun getAllStates(countryCode: String): Flow<ResponseResult<List<State>>>
    suspend fun saveAllStates(countryCode: String, data: List<State>)
    suspend fun getAllCities(
        countryCode: String,
        stateCode: String,
    ): Flow<ResponseResult<List<City>>>

    suspend fun saveAllCities(countryCode: String, stateCode: String, data: List<City>)
}

class OtherLocalDataSourceImpl : OtherLocalDataSource {
    private val cacheHelper = CacheHelper
    override suspend fun getAllCountries(): Flow<ResponseResult<List<Country>>> =
        cacheHelper.getData("getAllCountries").toCacheResult()

    override suspend fun saveAllCountries(data: List<Country>) {
        cacheHelper.saveData("getAllCountries", jsonConfig.encodeToString(data))
    }

    override suspend fun getAllStates(countryCode: String): Flow<ResponseResult<List<State>>> =
        cacheHelper.getData("getAllStates$countryCode").toCacheResult()

    override suspend fun saveAllStates(
        countryCode: String,
        data: List<State>,
    ) = cacheHelper.saveData("getAllStates$countryCode", jsonConfig.encodeToString(data))

    override suspend fun getAllCities(
        countryCode: String,
        stateCode: String,
    ): Flow<ResponseResult<List<City>>> =
        cacheHelper.getData("getAllCities$countryCode$stateCode").toCacheResult()

    override suspend fun saveAllCities(
        countryCode: String,
        stateCode: String,
        data: List<City>,
    ) = cacheHelper.saveData("getAllCities$countryCode$stateCode", jsonConfig.encodeToString(data))
}