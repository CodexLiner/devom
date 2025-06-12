package com.devom.models.payment

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserBankDetails(
    @SerialName("user_id")
    val userId: Int = 66,

    @SerialName("bank_name")
    val bankName: String = "HDFC Bank",

    @SerialName("account_number")
    val accountNumber: String = "1234567890",

    @SerialName("ifsc_code")
    val ifscCode: String = "HDFC0001234",

    @SerialName("account_holder_name")
    val accountHolderName: String = "Rajesh Kumar"
)
