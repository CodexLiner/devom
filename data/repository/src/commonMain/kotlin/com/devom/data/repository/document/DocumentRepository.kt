package com.devom.data.repository.document

import com.devom.data.cache.document.DocumentLocalDataSource
import com.devom.data.cache.document.DocumentLocalDataSourceImpl
import com.devom.data.server.document.DocumentRemoteDataSource
import com.devom.data.server.document.DocumentRemoteDataSourceImpl
import com.devom.models.document.CreateDocumentInput
import com.devom.models.document.GetDocumentResponse
import com.devom.models.document.UpdateDocumentInput
import com.devom.utils.cachepolicy.CachePolicy
import com.devom.utils.flow.apiFlow
import com.devom.utils.flow.cacheAwareFlow
import com.devom.utils.network.ResponseResult
import kotlinx.coroutines.flow.Flow

interface DocumentRepository {
    suspend fun getDocuments(userId: String,
        cachePolicy: CachePolicy = CachePolicy.CacheAndNetwork,
    ): Flow<ResponseResult<List<GetDocumentResponse>>>

    suspend fun removeDocument(documentId: String): Flow<ResponseResult<String>>
    suspend fun createDocument(input: CreateDocumentInput): Flow<ResponseResult<String>>
    suspend fun updateDocument(id: String, input: UpdateDocumentInput): Flow<ResponseResult<String>>
}

class DocumentRepositoryImpl() : DocumentRepository {
    private val documentRemoteDataSource: DocumentRemoteDataSource = DocumentRemoteDataSourceImpl()
    private val documentLocalDataSource: DocumentLocalDataSource = DocumentLocalDataSourceImpl()

    override suspend fun getDocuments(
        userId: String,
        cachePolicy: CachePolicy,
    ): Flow<ResponseResult<List<GetDocumentResponse>>> = cacheAwareFlow(
        cachePolicy = cachePolicy,
        saveCache = { documentLocalDataSource.saveDocuments(userId, it) },
        fetchCache = { documentLocalDataSource.getDocuments(userId) },
        fetchNetwork = { documentRemoteDataSource.getDocuments(userId) })

    override suspend fun removeDocument(documentId: String): Flow<ResponseResult<String>> =
        apiFlow { documentRemoteDataSource.removeDocument(documentId) }

    override suspend fun createDocument(input: CreateDocumentInput): Flow<ResponseResult<String>> = apiFlow { documentRemoteDataSource.createDocument(input) }

    override suspend fun updateDocument(id: String, input: UpdateDocumentInput, ): Flow<ResponseResult<String>> = apiFlow {
        documentRemoteDataSource.updateDocument(id, input)
    }

}