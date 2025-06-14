package com.devom.domain.payment

import com.devom.data.repository.payment.PaymentRepository
import com.devom.data.repository.payment.PaymentRepositoryImpl
import com.devom.models.payment.UserBankDetails
import com.devom.network.getUser
import com.devom.utils.cachepolicy.CachePolicy
import com.devom.utils.network.ResponseResult
import kotlinx.coroutines.flow.Flow

class GetBankDetailsUseCase {
    private val paymentRepository: PaymentRepository = PaymentRepositoryImpl()
    suspend operator fun invoke(cachePolicy: CachePolicy  = CachePolicy.CacheAndNetwork): Flow<ResponseResult<UserBankDetails>> {
      return  paymentRepository.getBankDetails(getUser().userId.toString() , cachePolicy)
    }
}