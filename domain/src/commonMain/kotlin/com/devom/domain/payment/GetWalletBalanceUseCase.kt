package com.devom.domain.payment

import com.devom.data.repository.payment.PaymentRepository
import com.devom.data.repository.payment.PaymentRepositoryImpl

class GetWalletBalanceUseCase {
    private val paymentRepository: PaymentRepository = PaymentRepositoryImpl()
    suspend operator fun invoke(userId: String) = paymentRepository.getWalletBalance(userId)
}