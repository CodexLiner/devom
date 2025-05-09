package com.devom.models.slots

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetAvailableSlotsResponse(
    @SerialName("id") val id: Int,
    @SerialName("pandit_id") val panditId: Int,
    @SerialName("available_date") val availableDate: String,
    @SerialName("start_time") val startTime: String,
    @SerialName("end_time") val endTime: String,
    @SerialName("is_booked") val isBooked: Int,
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") val updatedAt: String,
)