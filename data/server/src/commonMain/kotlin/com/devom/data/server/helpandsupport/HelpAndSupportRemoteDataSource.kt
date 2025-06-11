package com.devom.data.server.helpandsupport

import com.devom.data.server.endpoints.HelpAndSupportEndpoints
import com.devom.models.helpandsupport.CreateTicketRequest
import com.devom.models.helpandsupport.GetAllTicketsResponse
import com.devom.network.NetworkClient
import com.devom.network.utils.toMap
import com.devom.network.utils.toResponseResult
import com.devom.utils.network.ResponseResult
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import kotlinx.coroutines.flow.Flow

interface HelpAndSupportRemoteDataSource {
    suspend fun getAllTickets(): Flow<ResponseResult<List<GetAllTicketsResponse>>>
    suspend fun createTicket(createTicketRequest: CreateTicketRequest): Flow<ResponseResult<String>>
}

class HelpAndSupportRemoteDataSourceImpl() : HelpAndSupportRemoteDataSource {
    private val ktorClient = NetworkClient.ktorClient
    override suspend fun getAllTickets(): Flow<ResponseResult<List<GetAllTicketsResponse>>> =
        ktorClient.get(HelpAndSupportEndpoints.GetAllTickets).toResponseResult()

    override suspend fun createTicket(createTicketRequest: CreateTicketRequest): Flow<ResponseResult<String>> =
        ktorClient.post(HelpAndSupportEndpoints.CreateTicket) {
            setBody(
                MultiPartFormDataContent(
                    formData {
                        createTicketRequest.toMap().entries.map {
                            if (it.value.isNotBlank() && it.value::class != ByteArray::class) append(it.key, it.value)
                        }
                        append("image", createTicketRequest.image, Headers.build {
                            append(HttpHeaders.ContentType, "image/*")
                            append(
                                HttpHeaders.ContentDisposition,
                                "filename=\"${createTicketRequest.fileName}\""
                            )
                        })
                    })
            )
        }.toResponseResult()
}