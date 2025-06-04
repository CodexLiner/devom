package com.devom.domain.document

import com.devom.data.repository.document.DocumentRepository
import com.devom.data.repository.document.DocumentRepositoryImpl
import com.devom.models.document.UpdateDocumentInput

class UpdateDocumentUseCase {
    private val documentRepository : DocumentRepository = DocumentRepositoryImpl()
    suspend operator fun invoke(id : String , input : UpdateDocumentInput) = documentRepository.updateDocument(id,input)
}