package com.devom.data.repository.pandit

import com.devom.data.cache.pandit.PanditLocalDataSource
import com.devom.data.cache.pandit.PanditLocalDataSourceImpl
import com.devom.data.server.panditslots.PanditRemoteRepository
import com.devom.data.server.panditslots.PanditRemoteRepositoryImpl
import com.devom.models.pandit.CreateReviewInput
import com.devom.models.pandit.GetReviewsResponse
import com.devom.utils.cachepolicy.CachePolicy
import com.devom.utils.flow.apiFlow
import com.devom.utils.flow.cacheAwareFlow
import com.devom.utils.network.ResponseResult
import kotlinx.coroutines.flow.Flow

interface PanditRepository {
    suspend fun getPanditReviews(panditId: String , cachePolicy: CachePolicy = CachePolicy.CacheAndNetwork): Flow<ResponseResult<List<GetReviewsResponse>>>
    suspend fun createPanditReview(input: CreateReviewInput): Flow<ResponseResult<String>>
}

class PanditRepositoryImpl : PanditRepository {
    private val panditRemoteRepository: PanditRemoteRepository = PanditRemoteRepositoryImpl()
    private val panditLocalDataSource: PanditLocalDataSource = PanditLocalDataSourceImpl()

    override suspend fun getPanditReviews(
        panditId: String,
        cachePolicy: CachePolicy,
    ): Flow<ResponseResult<List<GetReviewsResponse>>>  = cacheAwareFlow(
        cachePolicy = cachePolicy,
        fetchCache = { panditLocalDataSource.getPanditReviews(panditId) },
        fetchNetwork = { panditRemoteRepository.getPanditReviews(panditId) },
        saveCache = { panditLocalDataSource.savePanditReviews(panditId, it) }
    )
    override suspend fun createPanditReview(input: CreateReviewInput): Flow<ResponseResult<String>> = apiFlow {
            panditRemoteRepository.createPanditReview(input)
    }
}