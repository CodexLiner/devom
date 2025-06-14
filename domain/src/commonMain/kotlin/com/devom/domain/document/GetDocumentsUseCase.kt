package com.devom.domain.document

import com.devom.data.repository.document.DocumentRepository
import com.devom.data.repository.document.DocumentRepositoryImpl
import com.devom.models.document.GetDocumentResponse
import com.devom.network.getUser
import com.devom.utils.cachepolicy.CachePolicy
import com.devom.utils.network.ResponseResult
import kotlinx.coroutines.flow.Flow

class GetDocumentsUseCase {
    private val documentRepository: DocumentRepository = DocumentRepositoryImpl()
    suspend operator fun invoke(cachePolicy: CachePolicy = CachePolicy.CacheAndNetwork): Flow<ResponseResult<List<GetDocumentResponse>>> {
        return documentRepository.getDocuments(getUser(). userId.toString() , cachePolicy)
    }
}