package com.devom.data.cache.pandit

import com.devom.data.cache.CacheHelper
import com.devom.data.cache.utils.jsonConfig
import com.devom.data.cache.utils.toCacheResult
import com.devom.models.pandit.GetReviewsResponse
import com.devom.utils.network.ResponseResult
import kotlinx.coroutines.flow.Flow

interface PanditLocalDataSource {
    suspend fun getPanditReviews(panditId: String): Flow<ResponseResult<GetReviewsResponse>>
    suspend fun savePanditReviews(panditId: String, reviews: GetReviewsResponse)
}

class PanditLocalDataSourceImpl : PanditLocalDataSource {
    private val cacheHelper = CacheHelper

    override suspend fun getPanditReviews(panditId: String): Flow<ResponseResult<GetReviewsResponse>> =
        cacheHelper.getData("getPanditReviews$panditId").toCacheResult()

    override suspend fun savePanditReviews(panditId: String, reviews: GetReviewsResponse) {
        cacheHelper.saveData(
            "getPanditReviews$panditId", jsonConfig.encodeToString(reviews)
        )
    }
}