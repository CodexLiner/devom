package com.devom.data.repository.helpandsupport

import com.devom.data.cache.helpandsupport.HelpAndSupportLocalDataSourceImpl
import com.devom.data.server.helpandsupport.HelpAndSupportRemoteDataSourceImpl
import com.devom.models.helpandsupport.CreateTicketRequest
import com.devom.models.helpandsupport.GetAllTicketsResponse
import com.devom.utils.cachepolicy.CachePolicy
import com.devom.utils.flow.apiFlow
import com.devom.utils.flow.cacheAwareFlow
import com.devom.utils.network.ResponseResult
import kotlinx.coroutines.flow.Flow

interface HelpAndSupportRepository {
    suspend fun getAllTickets(cachePolicy: CachePolicy = CachePolicy.CacheAndNetwork): Flow<ResponseResult<List<GetAllTicketsResponse>>>
    suspend fun createTicket(createTicketRequest: CreateTicketRequest): Flow<ResponseResult<String>>
    suspend fun getTicketDetails(ticketId: String): Flow<ResponseResult<GetAllTicketsResponse>>
}

class HelpAndSupportRepositoryImpl : HelpAndSupportRepository {
    private val helpAndSupportRemoteDataSource = HelpAndSupportRemoteDataSourceImpl()
    private val helpAndSupportLocalDataSource = HelpAndSupportLocalDataSourceImpl()

    override suspend fun getAllTickets(cachePolicy: CachePolicy): Flow<ResponseResult<List<GetAllTicketsResponse>>> =
        cacheAwareFlow(
            cachePolicy = cachePolicy,
            fetchCache = { helpAndSupportLocalDataSource.getAllTickets() },
            saveCache = { helpAndSupportLocalDataSource.saveAllTickets(it) },
            fetchNetwork = { helpAndSupportRemoteDataSource.getAllTickets() })

    override suspend fun createTicket(createTicketRequest: CreateTicketRequest): Flow<ResponseResult<String>> =
        apiFlow {
            helpAndSupportRemoteDataSource.createTicket(createTicketRequest)
        }

    override suspend fun getTicketDetails(ticketId: String): Flow<ResponseResult<GetAllTicketsResponse>> =
        apiFlow {
            helpAndSupportRemoteDataSource.getTicketDetails(ticketId)
        }


}