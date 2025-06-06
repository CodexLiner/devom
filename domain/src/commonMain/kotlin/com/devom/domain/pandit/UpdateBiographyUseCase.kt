package com.devom.domain.pandit

import com.devom.data.repository.pandit.PanditRepository
import com.devom.data.repository.pandit.PanditRepositoryImpl
import com.devom.models.pandit.UpdateBiographyInput

class UpdateBiographyUseCase {
    private val panditRepository: PanditRepository = PanditRepositoryImpl()
    suspend operator fun invoke(input: UpdateBiographyInput) = panditRepository.updateBiography(input)
}