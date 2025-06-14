package com.devom.domain.payment

import com.devom.data.repository.payment.PaymentRepository
import com.devom.data.repository.payment.PaymentRepositoryImpl
import com.devom.models.payment.GetWalletTransactionsResponse
import com.devom.network.getUser
import com.devom.utils.network.ResponseResult
import kotlinx.coroutines.flow.Flow

class GetWalletTransactionsUseCase {
    private val paymentRepository: PaymentRepository = PaymentRepositoryImpl()
    suspend operator fun invoke(): Flow<ResponseResult<GetWalletTransactionsResponse>> {
        return  paymentRepository.getTransactions(getUser().userId.toString())
    }
}