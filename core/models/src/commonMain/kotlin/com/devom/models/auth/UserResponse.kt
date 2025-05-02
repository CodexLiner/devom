package com.devom.models.auth
@Serializable
data class UserResponse(
    @SerialName("user_id") val userId: Int,
    @SerialName("fullname") val fullName: String,
    @SerialName("email") val email: String,
    @SerialName("password_hash") val passwordHash: String? = null,
    @SerialName("mobile_no") val mobileNo: String,
    @SerialName("city") val city: Int,
    @SerialName("state") val state: Int,
    @SerialName("country") val country: Int,
    @SerialName("date_of_birth") val dateOfBirth: String,
    @SerialName("profile_picture_url") val profilePictureUrl: String? = null,
    @SerialName("user_type_id") val userTypeId: Int,
    @SerialName("uuid") val uuid: String,
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") val updatedAt: String,
    @SerialName("access_token") val accessToken: String,
    @SerialName("refresh_token") val refreshToken: String,
    @SerialName("expires_at") val expiresAt: String
)
