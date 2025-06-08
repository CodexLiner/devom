package com.devom.models.payment
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class GetWalletBalanceResponse(
    @SerialName("balance") val balance: WalletBalance = WalletBalance()
)

@Serializable
data class WalletBalance(
    @SerialName("user_id") val userId: Int = 0,
    @SerialName("cash_wallet") val cashWallet: String = "",
    @SerialName("bonus_wallet") val bonusWallet: String = "",
    @SerialName("updated_at") val updatedAt: String = ""
)