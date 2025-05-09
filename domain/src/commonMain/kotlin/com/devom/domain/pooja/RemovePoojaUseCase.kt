package com.devom.domain.pooja

import com.devom.data.repository.pooja.PoojaRepository
import com.devom.data.repository.pooja.PoojaRepositoryImpl

class RemovePoojaUseCase {
    private val poojaRepository : PoojaRepository = PoojaRepositoryImpl()
    suspend operator fun invoke(poojaId : String) = poojaRepository.removePooja(poojaId)
}