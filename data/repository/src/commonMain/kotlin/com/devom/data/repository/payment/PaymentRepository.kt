package com.devom.data.repository.payment

import com.devom.data.server.payment.PaymentRemoteDataSource
import com.devom.data.server.payment.PaymentRemoteDataSourceImpl
import com.devom.models.payment.GetWalletBalanceResponse
import com.devom.models.payment.GetWalletTransactionsResponse
import com.devom.utils.flow.apiFlow
import com.devom.utils.network.ResponseResult
import kotlinx.coroutines.flow.Flow

interface PaymentRepository {
    suspend fun getWalletBalance(userId: String): Flow<ResponseResult<GetWalletBalanceResponse>>
    suspend fun getTransactions(userId: String): Flow<ResponseResult<GetWalletTransactionsResponse>>
}

class PaymentRepositoryImpl() : PaymentRepository {
    private val paymentRemoteDataSource: PaymentRemoteDataSource = PaymentRemoteDataSourceImpl()

    override suspend fun getWalletBalance(userId: String): Flow<ResponseResult<GetWalletBalanceResponse>> =
        apiFlow {
            paymentRemoteDataSource.getWalletBalance(userId)
        }

    override suspend fun getTransactions(userId: String): Flow<ResponseResult<GetWalletTransactionsResponse>> =
        apiFlow {
            paymentRemoteDataSource.getTransactions(userId)
        }

}
