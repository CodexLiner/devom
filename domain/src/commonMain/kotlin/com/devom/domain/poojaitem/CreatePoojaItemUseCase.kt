package com.devom.domain.poojaitem

import com.devom.data.repository.poojaitems.PoojaItemsRepository
import com.devom.data.repository.poojaitems.PoojaItemsRepositoryImpl
import com.devom.models.poojaitems.CreatePoojaItemInput

class CreatePoojaItemUseCase {
    private val poojaItemsRepository : PoojaItemsRepository = PoojaItemsRepositoryImpl()
    suspend operator fun invoke(poojaItemId : String, input : CreatePoojaItemInput) = poojaItemsRepository.updatePoojaItem(poojaItemId, input)

}