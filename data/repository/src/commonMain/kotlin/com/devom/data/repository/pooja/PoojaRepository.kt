package com.devom.data.repository.pooja

import com.devom.data.server.pooja.PoojaRemoteDataSource
import com.devom.data.server.pooja.PoojaRemoteDataSourceImpl
import com.devom.models.pooja.CreatePoojaInput
import com.devom.models.pooja.PoojaItem
import com.devom.models.pooja.PoojaItemMappingInput
import com.devom.utils.flow.apiFlow
import com.devom.utils.network.ResponseResult
import kotlinx.coroutines.flow.Flow

interface PoojaRepository {
    suspend fun getPooja(): Flow<ResponseResult<List<PoojaItem>>>
    suspend fun removePooja(poojaId: String): Flow<ResponseResult<String>>
    suspend fun createPooja(input: CreatePoojaInput): Flow<ResponseResult<String>>
    suspend fun updatePooja(poojaId: String, input: CreatePoojaInput): Flow<ResponseResult<String>>
    suspend fun createPoojaItemMapping(input: List<PoojaItemMappingInput>): Flow<ResponseResult<String>>
}

class PoojaRepositoryImpl() : PoojaRepository {
    private val poojaRemoteDataSource: PoojaRemoteDataSource = PoojaRemoteDataSourceImpl()

    /**
     * get pooja
     * @return Flow<ResponseResult<List<PoojaItem>>>
     */
    override suspend fun getPooja() = apiFlow { poojaRemoteDataSource.getPooja() }

    /**
     * remove pooja
     */
    override suspend fun removePooja(poojaId: String) = apiFlow {
        poojaRemoteDataSource.removePooja(poojaId)
    }

    /**
     * create pooja
     */
    override suspend fun createPooja(input: CreatePoojaInput) = apiFlow {
        poojaRemoteDataSource.createPooja(input)
    }

    /**
     * update pooja
     */
    override suspend fun updatePooja(poojaId: String, input: CreatePoojaInput, ) = apiFlow {
        poojaRemoteDataSource.updatePooja(poojaId,input)
    }

    /**
     * create pooja item mapping
     */
    override suspend fun createPoojaItemMapping(input: List<PoojaItemMappingInput>) = apiFlow {
        poojaRemoteDataSource.createPoojaItemMapping(input)
    }
}
