package com.devom.models.slots

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreatePanditSlotInput(
    @SerialName("pandit_id") val panditId: Int,
    @SerialName("slots") val slots: List<Slot>,
)

@Serializable
data class Slot(
    @SerialName("available_date") val availableDate: String,

    @SerialName("start_time") val startTime: String,

    @SerialName("end_time") val endTime: String,
)