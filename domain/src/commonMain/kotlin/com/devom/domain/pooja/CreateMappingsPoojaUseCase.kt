package com.devom.domain.pooja

import com.devom.data.repository.pooja.PoojaRepository
import com.devom.data.repository.pooja.PoojaRepositoryImpl
import com.devom.models.pooja.PoojaItemMappingInput

class CreateMappingsPoojaUseCase {
    private val poojaRepository : PoojaRepository = PoojaRepositoryImpl()

    suspend operator fun invoke(input: List<PoojaItemMappingInput>) = poojaRepository.createPoojaItemMapping(input)
}