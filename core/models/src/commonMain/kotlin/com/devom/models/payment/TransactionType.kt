package com.devom.models.payment

enum class TransactionType(val status : String) {
    DEBIT("debit"),
    CREDIT("credit"),
    WITHDRAWAL("withdrawal"),
    PAYMENT("payment")
}

enum class TransactionSource(val source : String) {
    CASH_WALLET("cash_wallet"),
    BONUS_WALLET("bonus_wallet")

}