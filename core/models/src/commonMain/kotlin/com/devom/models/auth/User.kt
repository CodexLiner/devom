package com.devom.models.auth

data class UserRequest(
    @SerializedName("fullname")
    val fullName: String = "",

    @SerializedName("email")
    val email: String = "",

    @SerializedName("mobile_no")
    val mobileNo: String = "",

    @SerializedName("city")
    val city: Int = 0,

    @SerializedName("state")
    val state: Int = 0,

    @SerializedName("country")
    val country: Int = 0,

    @SerializedName("date_of_birth")
    val dateOfBirth: String = "",

    @SerializedName("user_type_id")
    val userTypeId: Int = 0
)

