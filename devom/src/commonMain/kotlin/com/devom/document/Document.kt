package com.devom.document

import com.devom.domain.document.CreateDocumentUseCase
import com.devom.domain.document.GetDocumentsUseCase
import com.devom.domain.document.RemoveDocumentUseCase
import com.devom.domain.document.UpdateDocumentUseCase

class Document {
    val getDocumentsUseCase by lazy { GetDocumentsUseCase() }
    val createDocumentUseCase by lazy { CreateDocumentUseCase() }
    val updateDocumentUseCase by lazy { UpdateDocumentUseCase() }
    val removeDocumentUseCase by lazy { RemoveDocumentUseCase() }
}