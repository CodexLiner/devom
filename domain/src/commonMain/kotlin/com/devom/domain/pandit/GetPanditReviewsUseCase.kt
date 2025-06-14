package com.devom.domain.pandit

import com.devom.data.repository.pandit.PanditRepository
import com.devom.data.repository.pandit.PanditRepositoryImpl
import com.devom.network.getUser
import com.devom.utils.cachepolicy.CachePolicy

class GetPanditReviewsUseCase {
    private val panditRepository: PanditRepository = PanditRepositoryImpl()
    suspend operator fun invoke(
        cachePolicy: CachePolicy = CachePolicy.CacheAndNetwork,
    ) = panditRepository.getPanditReviews(getUser().userId.toString(), cachePolicy)
}