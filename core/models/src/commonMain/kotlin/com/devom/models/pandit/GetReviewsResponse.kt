package com.devom.models.pandit

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetReviewsResponse(
    @SerialName("average_rating")
    val averageRating: String = "",
    val reviews: List<Review> = mutableListOf<Review>(),
)

@Serializable
data class Review(
    @SerialName("review_id") val reviewId: String,

    @SerialName("booking_id") val bookingId: String,

    @SerialName("user_id") val userId: String,

    @SerialName("pandit_id") val panditId: String,

    @SerialName("pooja_id") val poojaId: String,

    @SerialName("rating") val rating: String,

    @SerialName("review_text") val reviewText: String,

    @SerialName("created_at") val createdAt: String,

    @SerialName("updated_at") val updatedAt: String,

    @SerialName("fullname") val userName: String = "",

    @SerialName("profile_picture_url") val userImage: String = "",
)