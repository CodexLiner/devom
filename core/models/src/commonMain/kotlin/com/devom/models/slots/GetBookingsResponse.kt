package com.devom.models.slots

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetBookingsResponse(
    @SerialName("booking_id") val bookingId: Int = 0,
    @SerialName("booking_code") val bookingCode: String = "",
    @SerialName("status") val status: String = "",
    @SerialName("booking_date") val bookingDate: String = "",
    @SerialName("item_ids") val itemIds: List<Int> = emptyList(),
    @SerialName("user_name") val userName: String = "",
    @SerialName("address") val address: String = "",
    @SerialName("mobile_no") val mobileNo: String = "",
    @SerialName("city") val city: Int = 0,
    @SerialName("state") val state: Int = 0,
    @SerialName("country") val country: Int = 0,
    @SerialName("pooja_name") val poojaName: String = "",
    @SerialName("pandit_name") val panditName: String = "",
    @SerialName("user_imagge") val userImage: String? = null,
    @SerialName("pooja_image") val poojaImage: String? = null,
    @SerialName("available_date") val availableDate: String = "",
    @SerialName("start_time") val startTime: String = "",
    @SerialName("end_time") val endTime: String = ""
)
