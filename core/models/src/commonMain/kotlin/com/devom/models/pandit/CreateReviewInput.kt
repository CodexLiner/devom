package com.devom.models.pandit

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateReviewInput(
    @SerialName("booking_id")
    val bookingId: String,

    @SerialName("user_id")
    val userId: String,

    @SerialName("pandit_id")
    val panditId: String,

    @SerialName("pooja_id")
    val poojaId: String,

    @SerialName("rating")
    val rating: String,

    @SerialName("review_text")
    val reviewText: String
)
