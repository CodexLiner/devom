package com.devom.domain.document

import com.devom.data.repository.document.DocumentRepository
import com.devom.data.repository.document.DocumentRepositoryImpl

class RemoveDocumentUseCase {
    private val documentRepository : DocumentRepository = DocumentRepositoryImpl()
    suspend operator fun invoke(documentId : String) = documentRepository.removeDocument(documentId)
}