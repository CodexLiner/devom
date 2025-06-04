package com.devom.domain.document

import com.devom.data.repository.document.DocumentRepository
import com.devom.data.repository.document.DocumentRepositoryImpl
import com.devom.utils.cachepolicy.CachePolicy

class GetDocumentsUseCase {
    private val documentRepository: DocumentRepository = DocumentRepositoryImpl()
    suspend operator fun invoke(userId: String , cachePolicy: CachePolicy = CachePolicy.CacheAndNetwork) = documentRepository.getDocuments(userId , cachePolicy)
}