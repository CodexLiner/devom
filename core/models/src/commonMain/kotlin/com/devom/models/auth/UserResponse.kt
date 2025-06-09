package com.devom.models.auth
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    @SerialName("user_id") val userId: Int = 0,
    @SerialName("fullname") var fullName: String = "",
    @SerialName("email") var email: String = "",
    @SerialName("password_hash") val passwordHash: String? = null,
    @SerialName("mobile_no") var mobileNo: String = "",
    @SerialName("city") var city: String = "",
    @SerialName("state") var state: String = "",
    @SerialName("country") var country: String = "",
    @SerialName("referral_code") var referralCode: String = "",
    @SerialName("review_rating") var reviewRating: Int = 0,
    @SerialName("date_of_birth") var dateOfBirth: String = "",
    @SerialName("address") var address: String = "",
    @SerialName("profile_picture_url") val profilePictureUrl: String? = null,
    @SerialName("user_type_id") val userTypeId: Int = 0,
    @SerialName("uuid") val uuid: String = "",
    @SerialName("created_at") val createdAt: String = "",
    @SerialName("updated_at") val updatedAt: String = "",
    @SerialName("access_token") val accessToken: String = "",
    @SerialName("refresh_token") val refreshToken: String = "",
    @SerialName("expires_at") val expiresAt: String = ""
)
