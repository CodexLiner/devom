package com.devom.models.document

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class GetDocumentResponse(
    @SerialName("document_id")
    val documentId: String = "",

    @SerialName("user_id")
    val userId: String = "",

    @SerialName("document_type")
    val documentType: String = "",

    @SerialName("document_url")
    val documentUrl: String = "",

    @SerialName("description")
    val description: String = "",

    @SerialName("upload_date")
    val uploadDate: String = "",

    @SerialName("verification_status")
    val verificationStatus: String = "",

    @SerialName("verifier_id")
    val verifierId: String? = null,

    @SerialName("verification_date")
    val verificationDate: String? = null,

    @SerialName("status")
    val status: String = "",

    @SerialName("is_deleted")
    val isDeleted: Int = 0,

    @SerialName("created_at")
    val createdAt: String = "",

    @SerialName("updated_at")
    val updatedAt: String = ""
)