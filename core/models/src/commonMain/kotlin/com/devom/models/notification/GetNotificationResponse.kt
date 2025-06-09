package com.devom.models.notification
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable



@Serializable
data class GetNotificationResponse(
    @SerialName("notification_id")
    val notificationId: Int = 4,

    @SerialName("user_id")
    val userId: Int = 66,

    @SerialName("title")
    val title: String = "document done",

    @SerialName("message")
    val message: String = "document added",

    @SerialName("type")
    val type: String = "document",

    @SerialName("is_read")
    val isRead: Int = 0,

    @SerialName("created_at")
    val createdAt: String = "2025-06-09T19:25:27.000Z"
)