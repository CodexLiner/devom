package com.devom.models.slots

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookPanditSlotInput(
    @SerialName("slot_id") val slotId: Int = 0,
    @SerialName("booking_start_time") val bookingStartTime: String = "",
    @SerialName("booking_end_time") val bookingEndTime: String = "",
    @SerialName("user_id") val userId: Int = 0,
    @SerialName("pooja_id") val poojaId: Int = 0,
    @SerialName("pandit_id") val panditId: Int = 0,
    @SerialName("item_ids") val itemIds: List<Int> = emptyList()
)
