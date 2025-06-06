package com.devom.models.document

import kotlinx.serialization.Serializable

@Serializable
data class CreateDocumentResponse (
    val filename : String? = ""
)