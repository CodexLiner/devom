package com.devom.domain.payment

import com.devom.data.repository.payment.PaymentRepository
import com.devom.data.repository.payment.PaymentRepositoryImpl
import com.devom.models.payment.UserBankDetails

class AddBankDetailsUseCase {
    private val paymentRepository: PaymentRepository = PaymentRepositoryImpl()
    suspend operator fun invoke(
        input: UserBankDetails,
    ) = paymentRepository.addBankAccount(input)
}