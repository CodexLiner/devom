package com.devom.models.slots

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreatePanditSlotInput(
    @SerialName("pandit_id") val panditId: Int = 0,
    @SerialName("slots") val slots: List<Slot> = emptyList()
)

@Serializable
data class Slot(
    @SerialName("available_date") val availableDate: String = "",
    @SerialName("start_time") var startTime: String = "",
    @SerialName("end_time") var endTime: String = ""
)
