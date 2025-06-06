package com.devom.data.server.pooja

import com.devom.data.server.endpoints.PoojaEndpoints
import com.devom.models.pooja.CreatePoojaInput
import com.devom.models.pooja.GetPoojaItemResponse
import com.devom.models.pooja.PoojaItemMappingInput
import com.devom.network.NetworkClient
import com.devom.network.utils.toResponseResult
import com.devom.utils.network.ResponseResult
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import kotlinx.coroutines.flow.Flow

interface PoojaRemoteDataSource {
    suspend fun getPooja(): Flow<ResponseResult<List<GetPoojaItemResponse>>>
    suspend fun removePooja(poojaId: String): Flow<ResponseResult<String>>
    suspend fun createPooja(input: CreatePoojaInput): Flow<ResponseResult<String>>
    suspend fun updatePooja(poojaId: String, input: CreatePoojaInput): Flow<ResponseResult<String>>
    suspend fun createPoojaItemMapping(input: List<PoojaItemMappingInput>): Flow<ResponseResult<String>>
}

class PoojaRemoteDataSourceImpl() : PoojaRemoteDataSource {
    private val ktorClient = NetworkClient.ktorClient

    override suspend fun getPooja(): Flow<ResponseResult<List<GetPoojaItemResponse>>> =
        ktorClient.get(PoojaEndpoints.GetPooja).toResponseResult()

    override suspend fun removePooja(poojaId: String): Flow<ResponseResult<String>> =
        ktorClient.delete(PoojaEndpoints.RemovePooja.plus("/$poojaId")).toResponseResult()

    override suspend fun createPooja(input: CreatePoojaInput): Flow<ResponseResult<String>> =
        ktorClient.post(PoojaEndpoints.CreatePooja){ setBody(input) }.toResponseResult()

    override suspend fun updatePooja(poojaId: String, input: CreatePoojaInput, ): Flow<ResponseResult<String>> =
        ktorClient.put(PoojaEndpoints.UpdatePooja.plus("/$poojaId")) { setBody(input) }.toResponseResult()

    override suspend fun createPoojaItemMapping(input: List<PoojaItemMappingInput>): Flow<ResponseResult<String>> =
        ktorClient.post(PoojaEndpoints.CreatePoojaItemMapping){ setBody(input) }.toResponseResult()


}