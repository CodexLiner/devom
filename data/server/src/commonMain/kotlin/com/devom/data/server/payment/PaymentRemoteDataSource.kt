package com.devom.data.server.payment

import com.devom.data.server.endpoints.PaymentEndpoints
import com.devom.models.payment.GetWalletBalanceResponse
import com.devom.models.payment.GetWalletTransactionsResponse
import com.devom.models.payment.UserBankDetails
import com.devom.network.NetworkClient
import com.devom.network.utils.toResponseResult
import com.devom.utils.network.ResponseResult
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow

interface PaymentRemoteDataSource {
    suspend fun getWalletBalance(userId: String): Flow<ResponseResult<GetWalletBalanceResponse>>
    suspend fun getTransactions(userId: String): Flow<ResponseResult<GetWalletTransactionsResponse>>
    suspend fun addBankAccount(input: UserBankDetails): Flow<ResponseResult<String>>
    suspend fun getBankDetails(userId: String): Flow<ResponseResult<UserBankDetails>>
}

class PaymentRemoteDataSourceImpl : PaymentRemoteDataSource {
    private val ktorClient = NetworkClient.ktorClient

    override suspend fun getWalletBalance(userId: String): Flow<ResponseResult<GetWalletBalanceResponse>> =
        ktorClient.get(PaymentEndpoints.GetWalletBalance.plus("/$userId")).toResponseResult()

    override suspend fun getTransactions(userId: String): Flow<ResponseResult<GetWalletTransactionsResponse>> =
        ktorClient.get(PaymentEndpoints.GetTransactions.plus("/$userId")).toResponseResult()

    override suspend fun addBankAccount(input: UserBankDetails): Flow<ResponseResult<String>> =
        ktorClient.get(PaymentEndpoints.AddBankAccount).toResponseResult()

    override suspend fun getBankDetails(userId: String): Flow<ResponseResult<UserBankDetails>> =
        ktorClient.get(PaymentEndpoints.AddBankAccount.plus("/$userId")).toResponseResult()

}
