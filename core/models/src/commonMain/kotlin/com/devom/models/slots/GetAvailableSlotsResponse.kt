package com.devom.models.slots

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetAvailableSlotsResponse(
    @SerialName("id") val id: String = "",
    @SerialName("pandit_id") val panditId: Int = 0,
    @SerialName("available_date") val availableDate: String = "",
    @SerialName("start_time") val startTime: String = "",
    @SerialName("end_time") val endTime: String = "",
    @SerialName("is_booked") val isBooked: Int = 0,
    @SerialName("created_at") val createdAt: String = "",
    @SerialName("updated_at") val updatedAt: String = ""
)
