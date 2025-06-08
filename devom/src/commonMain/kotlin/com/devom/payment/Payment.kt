package com.devom.payment

class Payment {
    val getWalletBalanceUseCase by lazy { com.devom.domain.payment.GetWalletBalanceUseCase() }
    val getWalletTransactionsUseCase by lazy { com.devom.domain.payment.GetWalletTransactionsUseCase() }
}