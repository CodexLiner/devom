package com.devom.domain.document

import com.devom.data.repository.document.DocumentRepository
import com.devom.data.repository.document.DocumentRepositoryImpl
import com.devom.models.document.CreateDocumentInput

class CreateDocumentUseCase {
    private val documentRepository : DocumentRepository = DocumentRepositoryImpl()
    suspend operator fun invoke(input: CreateDocumentInput) = documentRepository.createDocument(input)
}