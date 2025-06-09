package com.devom.data.repository.pandit

import com.devom.data.cache.pandit.PanditLocalDataSource
import com.devom.data.cache.pandit.PanditLocalDataSourceImpl
import com.devom.data.server.panditslots.PanditRemoteRepository
import com.devom.data.server.panditslots.PanditRemoteRepositoryImpl
import com.devom.models.pandit.CreateReviewInput
import com.devom.models.pandit.GetBiographyResponse
import com.devom.models.pandit.GetPanditPoojaResponse
import com.devom.models.pandit.GetReviewsResponse
import com.devom.models.pandit.MapPanditPoojaItemInput
import com.devom.models.pandit.UpdateBiographyInput
import com.devom.utils.cachepolicy.CachePolicy
import com.devom.utils.flow.apiFlow
import com.devom.utils.flow.cacheAwareFlow
import com.devom.utils.network.ResponseResult
import kotlinx.coroutines.flow.Flow

interface PanditRepository {
    suspend fun getPanditReviews(
        panditId: String,
        cachePolicy: CachePolicy = CachePolicy.CacheAndNetwork,
    ): Flow<ResponseResult<GetReviewsResponse>>

    suspend fun createPanditReview(input: CreateReviewInput): Flow<ResponseResult<String>>
    suspend fun updateBiography(input: UpdateBiographyInput): Flow<ResponseResult<String>>
    suspend fun getBiography(
        userId: String,
        cachePolicy: CachePolicy = CachePolicy.CacheAndNetwork,
    ): Flow<ResponseResult<GetBiographyResponse>>

    suspend fun mapPanditPoojaItem(input: MapPanditPoojaItemInput): Flow<ResponseResult<String>>
    suspend fun getPanditPooja(userId: String): Flow<ResponseResult<List<GetPanditPoojaResponse>>>
    suspend fun removePanditPoojaMapping(input: MapPanditPoojaItemInput): Flow<ResponseResult<String>>
}

class PanditRepositoryImpl : PanditRepository {
    private val panditRemoteRepository: PanditRemoteRepository = PanditRemoteRepositoryImpl()
    private val panditLocalDataSource: PanditLocalDataSource = PanditLocalDataSourceImpl()

    override suspend fun getPanditReviews(
        panditId: String,
        cachePolicy: CachePolicy,
    ): Flow<ResponseResult<GetReviewsResponse>> = cacheAwareFlow(
        cachePolicy = cachePolicy,
        fetchCache = { panditLocalDataSource.getPanditReviews(panditId) },
        fetchNetwork = { panditRemoteRepository.getPanditReviews(panditId) },
        saveCache = { panditLocalDataSource.savePanditReviews(panditId, it) }
    )

    override suspend fun createPanditReview(input: CreateReviewInput): Flow<ResponseResult<String>> =
        apiFlow {
            panditRemoteRepository.createPanditReview(input)
        }

    override suspend fun updateBiography(input: UpdateBiographyInput): Flow<ResponseResult<String>> =
        apiFlow {
            panditRemoteRepository.updateBiography(input)
        }

    override suspend fun getBiography(
        userId: String,
        cachePolicy: CachePolicy,
    ): Flow<ResponseResult<GetBiographyResponse>> =
        cacheAwareFlow(
            cachePolicy = cachePolicy,
            fetchCache = { panditLocalDataSource.getBiography(userId) },
            fetchNetwork = { panditRemoteRepository.getBiography(userId) },
            saveCache = { panditLocalDataSource.saveBiography(userId, it) }
        )

    override suspend fun mapPanditPoojaItem(input: MapPanditPoojaItemInput): Flow<ResponseResult<String>> =
        apiFlow {
            panditRemoteRepository.mapPanditPoojaItem(input)
        }

    override suspend fun getPanditPooja(userId: String): Flow<ResponseResult<List<GetPanditPoojaResponse>>> = cacheAwareFlow(
        cachePolicy = CachePolicy.CacheAndNetwork,
        fetchCache = { panditLocalDataSource.getPanditPooja(userId) },
        fetchNetwork = { panditRemoteRepository.getPanditPooja(userId) },
        saveCache = { panditLocalDataSource.savePanditPooja(userId, it) }
    )

    override suspend fun removePanditPoojaMapping(input: MapPanditPoojaItemInput): Flow<ResponseResult<String>>  = apiFlow {
        panditRemoteRepository.removePanditPoojaMapping(input)
    }

}