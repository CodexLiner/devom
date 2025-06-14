package com.devom.domain.pandit

import com.devom.data.repository.pandit.PanditRepository
import com.devom.data.repository.pandit.PanditRepositoryImpl
import com.devom.models.pandit.GetPanditPoojaResponse
import com.devom.network.getUser
import com.devom.utils.network.ResponseResult
import kotlinx.coroutines.flow.Flow

class GetPanditPoojaUseCase {
    private val panditRepository: PanditRepository = PanditRepositoryImpl()
    suspend operator fun invoke(): Flow<ResponseResult<List<GetPanditPoojaResponse>>> {
        return panditRepository.getPanditPooja(getUser().userId.toString())
    }
}