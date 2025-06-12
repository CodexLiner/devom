package com.devom.models.auth
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserRequestResponse(
    @SerialName("user_id") val userId: Int = 0,
    @SerialName("fullname") var fullName: String = "",
    @SerialName("email") var email: String = "",
    @SerialName("password_hash") val passwordHash: String? = null,
    @SerialName("mobile_no") var mobileNo: String = "",
    @SerialName("city") var city: String = "",
    @SerialName("state") var state: String = "",
    @SerialName("country") var country: String = "",
    @SerialName("referral_code") var referralCode: String = "",
    @SerialName("verified") var verified: String = "",
    @SerialName("profile_completion") var profileCompletion: Int = 0,
    @SerialName("review_rating") var reviewRating: Int = 0,
    @SerialName("date_of_birth") var dateOfBirth: String = "",
    @SerialName("address") var address: String = "",
    @SerialName("profile_picture_url") val profilePictureUrl: String? = null,
    @SerialName("user_type_id") var userTypeId: Int = 0,
    @SerialName("is_online") val isOnline: Int = 0,
    @SerialName("uuid") val uuid: String = "",
    @SerialName("created_at") val createdAt: String = "",
    @SerialName("updated_at") val updatedAt: String = "",
    @SerialName("access_token") val accessToken: String = "",
    @SerialName("refresh_token") val refreshToken: String = "",
    @SerialName("expires_at") val expiresAt: String = "",
    @SerialName("image_file_name") var imageFileName: String = ""
) {
    fun isValid(): Pair<Boolean, String?> {
        return when {
            fullName.isBlank() -> false to "Full name is required."
            email.isBlank() -> false to "Email is required."
            !email.matches(Regex("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$")) ->
                false to "Invalid email format."
            mobileNo.isBlank() -> false to "Mobile number is required."
            !mobileNo.matches(Regex("^\\d{10}$")) ->
                false to "Mobile number must be 10 digits."
            city.isBlank() -> false to "City is required."
            state.isBlank() -> false to "State is required."
            country.isBlank() -> false to "Country is required."
            dateOfBirth.isBlank() -> false to "Date of birth is required."
            address.isBlank() -> false to "Address is required."
            else -> true to null
        }
    }
}