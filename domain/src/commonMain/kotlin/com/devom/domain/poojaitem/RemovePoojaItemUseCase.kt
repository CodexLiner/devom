package com.devom.domain.poojaitem

import com.devom.data.repository.poojaitems.PoojaItemsRepository
import com.devom.data.repository.poojaitems.PoojaItemsRepositoryImpl

class RemovePoojaItemUseCase {
    private val poojaItemsRepository : PoojaItemsRepository = PoojaItemsRepositoryImpl()
    suspend operator fun invoke() = poojaItemsRepository.getPoojaItems()
}