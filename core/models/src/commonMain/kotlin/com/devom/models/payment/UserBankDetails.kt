package com.devom.models.payment

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserBankDetails(
    @SerialName("user_id")
    val userId: Int = 0,

    @SerialName("bank_name")
    var bankName: String = "",

    @SerialName("account_number")
    var accountNumber: String = "",

    @SerialName("ifsc_code")
    var ifscCode: String = "",

    @SerialName("account_holder_name")
    var accountHolderName: String = "",

    var file: ByteArray = byteArrayOf(),
) {
    fun isValid(): Pair<Boolean, String?> {
        return when {
            bankName.isBlank() -> false to "Bank name is required."
            accountNumber.isBlank() -> false to "Account number is required."
            !accountNumber.matches(Regex("^\\d{9,18}$")) ->
                false to "Account number must be between 9 to 18 digits."

            ifscCode.isBlank() -> false to "IFSC code is required."
            !ifscCode.matches(Regex("^[A-Z]{4}0[A-Z0-9]{6}$")) ->
                false to "Invalid IFSC code format."

            accountHolderName.isBlank() -> false to "Account holder name is required."
            else -> true to null
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as UserBankDetails

        if (userId != other.userId) return false
        if (bankName != other.bankName) return false
        if (accountNumber != other.accountNumber) return false
        if (ifscCode != other.ifscCode) return false
        if (accountHolderName != other.accountHolderName) return false
        if (!file.contentEquals(other.file)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = userId
        result = 31 * result + bankName.hashCode()
        result = 31 * result + accountNumber.hashCode()
        result = 31 * result + ifscCode.hashCode()
        result = 31 * result + accountHolderName.hashCode()
        result = 31 * result + file.contentHashCode()
        return result
    }
}
