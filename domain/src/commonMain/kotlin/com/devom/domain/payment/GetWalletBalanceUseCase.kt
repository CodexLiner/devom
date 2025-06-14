package com.devom.domain.payment

import com.devom.data.repository.payment.PaymentRepository
import com.devom.data.repository.payment.PaymentRepositoryImpl
import com.devom.models.payment.GetWalletBalanceResponse
import com.devom.network.getUser
import com.devom.utils.network.ResponseResult
import kotlinx.coroutines.flow.Flow

class GetWalletBalanceUseCase {
    private val paymentRepository: PaymentRepository = PaymentRepositoryImpl()
    suspend operator fun invoke(): Flow<ResponseResult<GetWalletBalanceResponse>> {
        return paymentRepository.getWalletBalance(getUser().userId.toString())
    }
}