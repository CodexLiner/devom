package com.devom.domain.pooja

import com.devom.data.repository.pooja.PoojaRepository
import com.devom.data.repository.pooja.PoojaRepositoryImpl
import com.devom.models.pooja.CreatePoojaInput

class UpdatePoojaUseCase {
    private val poojaRepository: PoojaRepository = PoojaRepositoryImpl()
    suspend operator fun invoke(poojaId: String, input: CreatePoojaInput) = poojaRepository.updatePooja(poojaId, input)
}