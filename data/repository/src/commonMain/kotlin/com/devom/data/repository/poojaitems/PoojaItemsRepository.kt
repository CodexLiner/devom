package com.devom.data.repository.poojaitems

import com.devom.data.server.poojaItems.PoojaItemRemoteDataSource
import com.devom.data.server.poojaItems.PoojaItemRemoteDataSourceImpl
import com.devom.models.poojaitems.CreatePoojaItemInput
import com.devom.models.poojaitems.GetPoojaItemsResponse
import com.devom.utils.flow.apiFlow
import com.devom.utils.network.ResponseResult
import kotlinx.coroutines.flow.Flow

interface PoojaItemsRepository {
    suspend fun getPoojaItems(): Flow<ResponseResult<List<GetPoojaItemsResponse>>>
    suspend fun createPoojaItem(input: CreatePoojaItemInput): Flow<ResponseResult<String>>
    suspend fun removePoojaItem(input: Map<String, String>): Flow<ResponseResult<String>>
    suspend fun updatePoojaItem(poojaItemId: String, input: CreatePoojaItemInput, ): Flow<ResponseResult<String>>
}

class PoojaItemsRepositoryImpl : PoojaItemsRepository {

    private val poojaItemRemoteDataSource: PoojaItemRemoteDataSource = PoojaItemRemoteDataSourceImpl()

    override suspend fun getPoojaItems() = apiFlow {   poojaItemRemoteDataSource.getPoojaItems() }

    override suspend fun createPoojaItem(input: CreatePoojaItemInput) = apiFlow {  poojaItemRemoteDataSource.createPoojaItem(input) }

    override suspend fun removePoojaItem(input: Map<String, String>) = apiFlow { poojaItemRemoteDataSource.removePoojaItem(input) }

    override suspend fun updatePoojaItem(poojaItemId: String, input: CreatePoojaItemInput) = apiFlow {  poojaItemRemoteDataSource.updatePoojaItem(poojaItemId, input) }

}