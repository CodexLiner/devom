package com.devom.models.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateUserRequest(
    @SerialName("fullname") var fullName: String = "",
    @SerialName("email") var email: String = "",
    @SerialName("mobile_no") var mobileNo: String = "",
    @SerialName("city") var city: String = "",
    @SerialName("state") var state: String = "",
    @SerialName("country") var country: String = "",
    @SerialName("date_of_birth") var dateOfBirth: String = "",
    @SerialName("user_type_id") var userTypeId: String = "1",
    @SerialName("address") var address: String = "",
    var referralCode: String = "",
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
            userTypeId.isBlank() -> false to "User type is required."
            address.isBlank() -> false to "Address is required."
            else -> true to null
        }
    }
}

@Serializable
data class CreateUserResponse(
    @SerialName("client_id") val clientId: Int = 0,

    @SerialName("client_name") val clientName: String = "",

    @SerialName("client_secret") val clientSecret: String = "",

    @SerialName("redirect_uri") val redirectUri: String = "",

    @SerialName("client_key") val clientKey: String = "",

    @SerialName("uuid") val uuid: String = "",

    @SerialName("encrypted_key") val encryptedKey: String = "",

    @SerialName("iv") val iv: String = "",

    @SerialName("created_at") val createdAt: String = "",

    @SerialName("updated_at") val updatedAt: String = "",
)

