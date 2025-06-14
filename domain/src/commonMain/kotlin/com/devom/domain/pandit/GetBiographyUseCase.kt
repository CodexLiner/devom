package com.devom.domain.pandit

import com.devom.data.repository.pandit.PanditRepository
import com.devom.data.repository.pandit.PanditRepositoryImpl
import com.devom.models.pandit.GetBiographyResponse
import com.devom.network.getUser
import com.devom.utils.cachepolicy.CachePolicy
import com.devom.utils.network.ResponseResult
import kotlinx.coroutines.flow.Flow

class GetBiographyUseCase {
    private val panditRepository: PanditRepository = PanditRepositoryImpl()
    suspend operator fun invoke(cachePolicy: CachePolicy = CachePolicy.CacheAndNetwork): Flow<ResponseResult<GetBiographyResponse>> {
        return panditRepository.getBiography(getUser().userId.toString(),cachePolicy)
    }
}

