package com.devom.data.server.panditslots

import com.devom.data.server.endpoints.PanditEndpoints
import com.devom.models.pandit.CreateReviewInput
import com.devom.models.pandit.GetBiographyResponse
import com.devom.models.pandit.GetPanditPoojaResponse
import com.devom.models.pandit.GetReviewsResponse
import com.devom.models.pandit.MapPanditPoojaItemInput
import com.devom.models.pandit.UpdateBiographyInput
import com.devom.network.NetworkClient
import com.devom.network.utils.toResponseResult
import com.devom.utils.network.ResponseResult
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.coroutines.flow.Flow

interface PanditRemoteRepository {
    suspend fun getPanditReviews(panditId: String): Flow<ResponseResult<GetReviewsResponse>>
    suspend fun createPanditReview(input: CreateReviewInput): Flow<ResponseResult<String>>
    suspend fun updateBiography(input: UpdateBiographyInput): Flow<ResponseResult<String>>
    suspend fun getBiography(userId : String) : Flow<ResponseResult<GetBiographyResponse>>
    suspend fun mapPanditPoojaItem(input: MapPanditPoojaItemInput): Flow<ResponseResult<String>>
    suspend fun getPanditPooja(userId : String) : Flow<ResponseResult<List<GetPanditPoojaResponse>>>
}

class PanditRemoteRepositoryImpl : PanditRemoteRepository {
    private val ktorClient = NetworkClient.ktorClient
    override suspend fun getPanditReviews(panditId: String): Flow<ResponseResult<GetReviewsResponse>> =
        ktorClient.get(PanditEndpoints.GetReviews.plus("/$panditId")).toResponseResult()

    override suspend fun createPanditReview(input: CreateReviewInput): Flow<ResponseResult<String>> =
        ktorClient.post(PanditEndpoints.CreateReview) { setBody(input) }.toResponseResult()

    override suspend fun updateBiography(input: UpdateBiographyInput): Flow<ResponseResult<String>> =
        ktorClient.post(PanditEndpoints.UpdateBiography) { setBody(input) }.toResponseResult()

    override suspend fun getBiography(userId: String): Flow<ResponseResult<GetBiographyResponse>> =
        ktorClient.get(PanditEndpoints.GetBiography.plus("/$userId")).toResponseResult()

    override suspend fun mapPanditPoojaItem(input: MapPanditPoojaItemInput): Flow<ResponseResult<String>>  =
        ktorClient.post(PanditEndpoints.MapPanditPoojaItem) { setBody(input) }.toResponseResult()

    override suspend fun getPanditPooja(userId: String): Flow<ResponseResult<List<GetPanditPoojaResponse>>> =
        ktorClient.get(PanditEndpoints.GetPanditPooja.plus("/$userId/poojas")).toResponseResult()
}

