package com.devom.data.server.document

import com.devom.data.server.endpoints.DocumentEndpoints
import com.devom.models.document.CreateDocumentInput
import com.devom.models.document.GetDocumentResponse
import com.devom.models.document.UpdateDocumentInput
import com.devom.network.NetworkClient
import com.devom.network.utils.toResponseResult
import com.devom.utils.network.ResponseResult
import io.ktor.client.request.delete
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import kotlinx.coroutines.flow.Flow

interface DocumentRemoteDataSource {
    suspend fun getDocuments(userId: String): Flow<ResponseResult<List<GetDocumentResponse>>>
    suspend fun removeDocument(documentId: String): Flow<ResponseResult<String>>
    suspend fun createDocument(input: CreateDocumentInput): Flow<ResponseResult<String>>
    suspend fun updateDocument(id: String, input: UpdateDocumentInput): Flow<ResponseResult<String>>
}

class DocumentRemoteDataSourceImpl() : DocumentRemoteDataSource {
    val ktorClient = NetworkClient.ktorClient
    override suspend fun getDocuments(userId: String): Flow<ResponseResult<List<GetDocumentResponse>>> =
        ktorClient.get(DocumentEndpoints.GetDocuments.path.plus("/$userId")).toResponseResult()


    override suspend fun removeDocument(documentId: String): Flow<ResponseResult<String>> =
        ktorClient.delete(DocumentEndpoints.RemoveDocument.path.plus("/$documentId"))
            .toResponseResult()


    override suspend fun createDocument(input: CreateDocumentInput): Flow<ResponseResult<String>> =
        ktorClient.post(DocumentEndpoints.CreateDocument.path) {
            setBody(
                MultiPartFormDataContent(
                    formData {
                        append("user_id", input.userId.toString())
                        append("document_type", input.documentType)
                        append("description", input.description)
                        append("document_name", input.documentName)
                        append("file", input.file, Headers.build {
                            append(HttpHeaders.ContentType, input.mimeType)
                            append(HttpHeaders.ContentDisposition, "filename=\"${input.documentName}\"")

                        })
                    }
                )
            )
        }.toResponseResult()


    override suspend fun updateDocument(
        id: String,
        input: UpdateDocumentInput,
    ): Flow<ResponseResult<String>> =
        ktorClient.post(DocumentEndpoints.UpdateDocument.path.plus("/$id")) { setBody(input) }
            .toResponseResult()

}