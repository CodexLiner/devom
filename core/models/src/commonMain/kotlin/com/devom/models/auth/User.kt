package com.devom.models.auth
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserRequest(
    @SerialName("fullname")
    val fullName: String = "",

    @SerialName("email")
    val email: String = "",

    @SerialName("mobile_no")
    val mobileNo: String = "",

    @SerialName("city")
    val city: Int = 0,

    @SerialName("state")
    val state: Int = 0,

    @SerialName("country")
    val country: Int = 0,

    @SerialName("date_of_birth")
    val dateOfBirth: String = "",

    @SerialName("user_type_id")
    val userTypeId: Int = 0
)


