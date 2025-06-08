package com.devom.models.payment
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetWalletTransactionsResponse(
    @SerialName("transactions") val transactions: List<WalletTransaction> = emptyList()
)

@Serializable
data class WalletTransaction(
    @SerialName("transaction_id") val transactionId: Int = 0,
    @SerialName("user_id") val userId: Int = 0,
    @SerialName("amount") val amount: String = "",
    @SerialName("type") val type: String = "",         // e.g., "credit" / "debit"
    @SerialName("source") val source: String = "",     // e.g., "cash_wallet" / "bonus_wallet"
    @SerialName("purpose") val purpose: String = "",   // e.g., "booking" / "payment"
    @SerialName("created_at") val createdAt: String = ""
)