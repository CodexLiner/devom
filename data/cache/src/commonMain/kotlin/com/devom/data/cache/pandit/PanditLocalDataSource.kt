package com.devom.data.cache.pandit

import com.devom.data.cache.CacheHelper
import com.devom.data.cache.utils.jsonConfig
import com.devom.data.cache.utils.toCacheResult
import com.devom.models.pandit.GetBiographyResponse
import com.devom.models.pandit.GetPanditPoojaResponse
import com.devom.models.pandit.GetReviewsResponse
import com.devom.utils.network.ResponseResult
import kotlinx.coroutines.flow.Flow

interface PanditLocalDataSource {
    suspend fun getPanditReviews(panditId: String): Flow<ResponseResult<GetReviewsResponse>>
    suspend fun savePanditReviews(panditId: String, reviews: GetReviewsResponse)
    suspend fun getBiography(userId: String): Flow<ResponseResult<GetBiographyResponse>>
    suspend fun saveBiography(userId: String, biography: GetBiographyResponse)
    suspend fun getPanditPooja(userId: String): Flow<ResponseResult<List<GetPanditPoojaResponse>>>
    suspend fun savePanditPooja(userId: String, reviews: List<GetPanditPoojaResponse>)
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

    override suspend fun getBiography(userId: String): Flow<ResponseResult<GetBiographyResponse>> =
        cacheHelper.getData("getBiography$userId").toCacheResult()

    override suspend fun saveBiography(userId: String, biography: GetBiographyResponse) {
        cacheHelper.saveData(
            "getBiography$userId", jsonConfig.encodeToString(biography)
        )
    }

    override suspend fun getPanditPooja(userId: String): Flow<ResponseResult<List<GetPanditPoojaResponse>>> =
        cacheHelper.getData("getPanditPooja$userId").toCacheResult()

    override suspend fun savePanditPooja(
        userId: String,
        reviews: List<GetPanditPoojaResponse>,
    ) {
        cacheHelper.saveData(
            "getPanditPooja$userId", jsonConfig.encodeToString(reviews)
        )
    }
}