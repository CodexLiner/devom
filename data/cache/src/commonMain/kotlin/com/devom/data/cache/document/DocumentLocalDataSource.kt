package com.devom.data.cache.document

import com.devom.data.cache.CacheHelper
import com.devom.data.cache.utils.jsonConfig
import com.devom.data.cache.utils.toCacheResult
import com.devom.models.document.GetDocumentResponse
import com.devom.utils.network.ResponseResult
import kotlinx.coroutines.flow.Flow

interface DocumentLocalDataSource {
    suspend fun getDocuments(userId: String): Flow<ResponseResult<List<GetDocumentResponse>>>
    suspend fun saveDocuments(userId: String, data: List<GetDocumentResponse>)
}

class DocumentLocalDataSourceImpl : DocumentLocalDataSource {
    private val cacheHelper = CacheHelper

    override suspend fun getDocuments(userId: String): Flow<ResponseResult<List<GetDocumentResponse>>> =
        cacheHelper.getData("getDocuments$userId").toCacheResult()

    override suspend fun saveDocuments(userId: String, data: List<GetDocumentResponse>) {
        cacheHelper.saveData("getDocuments$userId", jsonConfig.encodeToString(data))
    }

}