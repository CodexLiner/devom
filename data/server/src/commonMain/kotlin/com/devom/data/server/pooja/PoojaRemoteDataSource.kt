package com.devom.data.server.pooja

import com.devom.data.server.endpoints.PoojaEndPoints
import com.devom.models.pooja.PoojaItem
import com.devom.network.NetworkClient
import com.devom.network.utils.toResponseResult
import com.devom.utils.flow.apiFlow
import com.devom.utils.network.ResponseResult
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow

interface PoojaRemoteDataSource {
    suspend fun getPooja(): Flow<ResponseResult<List<PoojaItem>>>
}

class PoojaRemoteDataSourceImpl() : PoojaRemoteDataSource {
    private val ktorClient = NetworkClient.ktorClient

    override suspend fun getPooja(): Flow<ResponseResult<List<PoojaItem>>> =
        ktorClient.get(PoojaEndPoints.GetPooja.path).toResponseResult()


}