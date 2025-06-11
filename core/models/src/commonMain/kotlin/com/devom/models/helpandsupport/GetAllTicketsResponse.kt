package com.devom.models.helpandsupport

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetAllTicketsResponse(
    @SerialName("ticket_id")
    val ticketId: Int = 0,

    @SerialName("user_id")
    val userId: Int = 0,

    @SerialName("subject")
    val subject: String = "",

    @SerialName("message")
    val message: String = "",

    @SerialName("status")
    val status: String = "open",

    @SerialName("priority")
    val priority: String = "low",

    @SerialName("response")
    val response: String? = null,

    @SerialName("admin_id")
    val adminId: Int? = null,

    @SerialName("image")
    val image: String = "",

    @SerialName("created_at")
    val createdAt: String = "",

    @SerialName("updated_at")
    val updatedAt: String = "",

    @SerialName("ticket_code")
    val ticketCode: String = ""
)
