package com.devom.models.helpandsupport

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateTicketRequest(
    @SerialName("user_id")
    val userId: Int = 0,

    @SerialName("subject")
    val subject: String = "",

    @SerialName("message")
    val message: String = "",

    @SerialName("priority")
    val priority: String = "low",

    @SerialName("image") val image: ByteArray = byteArrayOf(),
    @SerialName("file_name") val fileName: String = "",

    ) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as CreateTicketRequest

        if (userId != other.userId) return false
        if (subject != other.subject) return false
        if (message != other.message) return false
        if (priority != other.priority) return false
        if (!image.contentEquals(other.image)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = userId
        result = 31 * result + subject.hashCode()
        result = 31 * result + message.hashCode()
        result = 31 * result + priority.hashCode()
        result = 31 * result + image.contentHashCode()
        return result
    }
}
