package com.devom.data.repository.pooja

import com.devom.data.server.pooja.PoojaRemoteDataSource
import com.devom.data.server.pooja.PoojaRemoteDataSourceImpl
import com.devom.models.pooja.PoojaItem
import com.devom.utils.flow.apiFlow
import com.devom.utils.network.ResponseResult
import kotlinx.coroutines.flow.Flow

interface PoojaRepository {
    fun getPooja(): Flow<ResponseResult<List<PoojaItem>>>
}

class PoojaRepositoryImpl() : PoojaRepository {
    private val poojaRemoteDataSource: PoojaRemoteDataSource = PoojaRemoteDataSourceImpl()

    /**
     * get pooja
     * @return Flow<ResponseResult<List<PoojaItem>>>
     */
    override fun getPooja(): Flow<ResponseResult<List<PoojaItem>>> = apiFlow {
        poojaRemoteDataSource.getPooja()
    }

}
