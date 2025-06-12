package com.devom.data.repository.payment

import com.devom.data.cache.payment.PaymentLocalDataSource
import com.devom.data.cache.payment.PaymentLocalDataSourceImpl
import com.devom.data.server.payment.PaymentRemoteDataSource
import com.devom.data.server.payment.PaymentRemoteDataSourceImpl
import com.devom.models.payment.GetWalletBalanceResponse
import com.devom.models.payment.GetWalletTransactionsResponse
import com.devom.models.payment.UserBankDetails
import com.devom.utils.cachepolicy.CachePolicy
import com.devom.utils.flow.apiFlow
import com.devom.utils.flow.cacheAwareFlow
import com.devom.utils.network.ResponseResult
import kotlinx.coroutines.flow.Flow

interface PaymentRepository {
    suspend fun getWalletBalance(
        userId: String,
        cachePolicy: CachePolicy = CachePolicy.CacheAndNetwork,
    ): Flow<ResponseResult<GetWalletBalanceResponse>>

    suspend fun getTransactions(
        userId: String,
        cachePolicy: CachePolicy = CachePolicy.CacheAndNetwork,
    ): Flow<ResponseResult<GetWalletTransactionsResponse>>

    suspend fun addBankAccount(input: UserBankDetails): Flow<ResponseResult<String>>
    suspend fun getBankDetails(
        userId: String,
        cachePolicy: CachePolicy = CachePolicy.CacheAndNetwork,
    ): Flow<ResponseResult<UserBankDetails>>
}

class PaymentRepositoryImpl() : PaymentRepository {
    private val paymentRemoteDataSource: PaymentRemoteDataSource = PaymentRemoteDataSourceImpl()
    private val paymentLocalDataSource: PaymentLocalDataSource = PaymentLocalDataSourceImpl()

    override suspend fun getWalletBalance(
        userId: String,
        cachePolicy: CachePolicy,
    ): Flow<ResponseResult<GetWalletBalanceResponse>> =
        cacheAwareFlow(
            cachePolicy = cachePolicy,
            fetchCache = { paymentLocalDataSource.getWalletBalance(userId) },
            fetchNetwork = { paymentRemoteDataSource.getWalletBalance(userId) },
            saveCache = { paymentLocalDataSource.saveWalletBalance(userId, it) }

        )

    override suspend fun getTransactions(
        userId: String,
        cachePolicy: CachePolicy,
    ): Flow<ResponseResult<GetWalletTransactionsResponse>> =
        cacheAwareFlow(
            cachePolicy = cachePolicy,
            fetchCache = { paymentLocalDataSource.getTransactions(userId) },
            fetchNetwork = { paymentRemoteDataSource.getTransactions(userId) },
            saveCache = { paymentLocalDataSource.saveTransactions(userId, it) }
        )

    override suspend fun addBankAccount(input: UserBankDetails): Flow<ResponseResult<String>> =
        apiFlow {
            paymentRemoteDataSource.addBankAccount(input)
        }

    override suspend fun getBankDetails(
        userId: String,
        cachePolicy: CachePolicy,
    ): Flow<ResponseResult<UserBankDetails>> = cacheAwareFlow(
        cachePolicy = cachePolicy,
        fetchCache = { paymentLocalDataSource.getBankDetails(userId) },
        fetchNetwork = { paymentRemoteDataSource.getBankDetails(userId) },
        saveCache = { paymentLocalDataSource.saveBankDetails(userId, it) }
    )
}
