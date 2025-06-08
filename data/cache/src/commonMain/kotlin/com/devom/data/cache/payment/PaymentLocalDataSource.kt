package com.devom.data.cache.payment

import com.devom.data.cache.CacheHelper
import com.devom.data.cache.utils.jsonConfig
import com.devom.data.cache.utils.toCacheResult
import com.devom.models.payment.GetWalletBalanceResponse
import com.devom.models.payment.GetWalletTransactionsResponse
import com.devom.utils.network.ResponseResult
import kotlinx.coroutines.flow.Flow

interface PaymentLocalDataSource {
    suspend fun getWalletBalance(userId: String): Flow<ResponseResult<GetWalletBalanceResponse>>
    suspend fun getTransactions(userId: String): Flow<ResponseResult<GetWalletTransactionsResponse>>
    suspend fun saveWalletBalance(userId: String, data: GetWalletBalanceResponse)
    suspend fun saveTransactions(userId: String, data: GetWalletTransactionsResponse)
}

class PaymentLocalDataSourceImpl : PaymentLocalDataSource {
    val cacheHelper = CacheHelper
    override suspend fun getWalletBalance(userId: String): Flow<ResponseResult<GetWalletBalanceResponse>> {
        return cacheHelper.getData("getWalletBalance$userId").toCacheResult()
    }

    override suspend fun saveWalletBalance(userId: String, data: GetWalletBalanceResponse) {
        cacheHelper.saveData("getWalletBalance$userId", jsonConfig.encodeToString(data))
    }

    override suspend fun saveTransactions(userId: String, data: GetWalletTransactionsResponse) {
        cacheHelper.saveData("getTransactions$userId", jsonConfig.encodeToString(data))
    }

    override suspend fun getTransactions(userId: String): Flow<ResponseResult<GetWalletTransactionsResponse>> {
        return cacheHelper.getData("getTransactions$userId").toCacheResult()

    }

}