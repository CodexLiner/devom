package com.devom.domain.pandit

import com.devom.data.repository.pandit.PanditRepository
import com.devom.data.repository.pandit.PanditRepositoryImpl
import com.devom.models.pandit.MapPanditPoojaItemInput
import com.devom.network.getUser
import com.devom.utils.network.ResponseResult
import kotlinx.coroutines.flow.Flow

class MapPanditPoojaItemUseCase {
    private val panditRepository: PanditRepository = PanditRepositoryImpl()
    suspend operator fun invoke(input: MapPanditPoojaItemInput): Flow<ResponseResult<String>> {
        return panditRepository.mapPanditPoojaItem(input.copy(panditId = getUser().userId))
    }
}