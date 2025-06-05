package com.devom.models.slots

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateBookingStatusInput (
    @SerialName("booking_id") val id: Int,
    @SerialName("status") val status: String

)