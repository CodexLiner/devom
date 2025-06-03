package com.devom.data.server.panditslots

import com.devom.data.server.endpoints.ReviewsEndpoints
import com.devom.models.pandit.CreateReviewInput
import com.devom.models.pandit.GetReviewsResponse
import com.devom.network.NetworkClient
import com.devom.network.utils.toResponseResult
import com.devom.utils.network.ResponseResult
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.coroutines.flow.Flow

interface PanditRemoteRepository {
    suspend fun getPanditReviews(panditId: String): Flow<ResponseResult<List<GetReviewsResponse>>>
    suspend fun createPanditReview(input: CreateReviewInput): Flow<ResponseResult<String>>
}

class PanditRemoteRepositoryImpl : PanditRemoteRepository {
    private val ktorClient = NetworkClient.ktorClient
    override suspend fun getPanditReviews(panditId: String): Flow<ResponseResult<List<GetReviewsResponse>>> =
        ktorClient.get(ReviewsEndpoints.GetReviews.plus("/$panditId")).toResponseResult()

    override suspend fun createPanditReview(input: CreateReviewInput): Flow<ResponseResult<String>> =
        ktorClient.post(ReviewsEndpoints.CreateReview) { setBody(input) }.toResponseResult()
}

