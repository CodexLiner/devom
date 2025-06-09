package com.devom.domain.pandit

import com.devom.data.repository.pandit.PanditRepository
import com.devom.data.repository.pandit.PanditRepositoryImpl
import com.devom.models.pandit.MapPanditPoojaItemInput

class RemovePanditPoojaMappingUseCase  {
    private val panditRepository: PanditRepository = PanditRepositoryImpl()
    suspend operator fun invoke(input: MapPanditPoojaItemInput) = panditRepository.removePanditPoojaMapping(input)
}