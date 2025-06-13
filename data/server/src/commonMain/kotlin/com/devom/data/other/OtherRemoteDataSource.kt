package com.devom.data.other

import com.devom.data.server.endpoints.OtherEndpoints
import com.devom.models.other.City
import com.devom.models.other.Country
import com.devom.models.other.State
import com.devom.network.NetworkClient
import com.devom.network.utils.toResponseResult
import com.devom.utils.network.ResponseResult
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow

interface OtherRemoteDataSource {
    suspend fun getCountryList(): Flow<ResponseResult<List<Country>>>
    suspend fun getStateList(countryCode: String): Flow<ResponseResult<List<State>>>
    suspend fun getCityList(
        countryCode: String,
        stateCode: String,
    ): Flow<ResponseResult<List<City>>>
}

class OtherRemoteDataSourceImpl : OtherRemoteDataSource {
    private val ktorClient = NetworkClient.ktorClient
    override suspend fun getCountryList(): Flow<ResponseResult<List<Country>>> =
        ktorClient.get(OtherEndpoints.GetCountryList).toResponseResult()

    override suspend fun getStateList(countryCode: String): Flow<ResponseResult<List<State>>> =
        ktorClient.get(OtherEndpoints.GetStateList.plus("/$countryCode")).toResponseResult()

    override suspend fun getCityList(
        countryCode: String,
        stateCode: String,
    ): Flow<ResponseResult<List<City>>> =
        ktorClient.get(OtherEndpoints.GetCityList.plus("/$countryCode/$stateCode")).toResponseResult()

}