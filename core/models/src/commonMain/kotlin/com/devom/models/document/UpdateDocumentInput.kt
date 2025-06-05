package com.devom.models.document
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class UpdateDocumentInput(
    @SerialName("status")
    val status: String = "verified",

    @SerialName("verifier_id")
    val verifierId: Int = 0
)