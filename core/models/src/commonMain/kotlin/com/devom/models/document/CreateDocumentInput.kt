package com.devom.models.document

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class CreateDocumentInput(
    @SerialName("user_id") val userId: String = "",
    @SerialName("document_type") val documentType: String = "",
    @SerialName("description") val description: String = "",
    @SerialName("file") val file: ByteArray = byteArrayOf(),
    @SerialName("document_name") val documentName: String = "",
    @SerialName("mime_type") val mimeType: String = "",
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as CreateDocumentInput

        if (userId != other.userId) return false
        if (documentType != other.documentType) return false
        if (description != other.description) return false
        if (!file.contentEquals(other.file)) return false
        if (documentName != other.documentName) return false

        return true
    }

    override fun hashCode(): Int {
        var result = userId.hashCode()
        result = 31 * result + documentType.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + file.contentHashCode()
        result = 31 * result + documentName.hashCode()
        result = 31 * result + mimeType.hashCode()
        return result
    }

}
