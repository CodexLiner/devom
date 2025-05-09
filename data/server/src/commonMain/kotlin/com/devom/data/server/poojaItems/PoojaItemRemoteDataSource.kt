package com.devom.data.server.poojaItems

import com.devom.data.server.endpoints.PoojaItemEndPoints
import com.devom.models.poojaitems.CreatePoojaItemInput
import com.devom.models.poojaitems.GetPoojaItemsResponse
import com.devom.network.NetworkClient
import com.devom.network.utils.setParams
import com.devom.network.utils.toResponseResult
import com.devom.utils.network.ResponseResult
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import kotlinx.coroutines.flow.Flow

interface PoojaItemRemoteDataSource {
    suspend fun getPoojaItems(): Flow<ResponseResult<List<GetPoojaItemsResponse>>>
    suspend fun createPoojaItem(input: CreatePoojaItemInput): Flow<ResponseResult<String>>
    suspend fun removePoojaItem(input : Map<String , String>): Flow<ResponseResult<String>>
    suspend fun updatePoojaItem(poojaItemId: String, input: CreatePoojaItemInput): Flow<ResponseResult<String>>
}

class PoojaItemRemoteDataSourceImpl : PoojaItemRemoteDataSource {
    private val ktorClient = NetworkClient.ktorClient

    override suspend fun getPoojaItems(): Flow<ResponseResult<List<GetPoojaItemsResponse>>>  =
        ktorClient.get(PoojaItemEndPoints.GetPoojaItems.path).toResponseResult()

    override suspend fun createPoojaItem(input: CreatePoojaItemInput): Flow<ResponseResult<String>> =
        ktorClient.post(PoojaItemEndPoints.CreatePoojaItem.path){ setBody(input) }.toResponseResult()

    override suspend fun removePoojaItem(input: Map<String, String>): Flow<ResponseResult<String>> =
        ktorClient.delete(PoojaItemEndPoints.RemovePoojaItem.path){ setParams(input) }.toResponseResult()

    override suspend fun updatePoojaItem(poojaItemId: String, input: CreatePoojaItemInput, ): Flow<ResponseResult<String>> =
        ktorClient.put(PoojaItemEndPoints.UpdatePoojaItem.path.plus("/$poojaItemId")) { setBody(input) }.toResponseResult()
}