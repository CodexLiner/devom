package com.devom.domain.pandit

import com.devom.data.repository.pandit.PanditRepository
import com.devom.data.repository.pandit.PanditRepositoryImpl
import com.devom.models.pandit.CreateReviewInput

class CreatePanditReviewUseCase {
    private val panditRepository: PanditRepository = PanditRepositoryImpl()
    suspend operator fun invoke(input: CreateReviewInput) = panditRepository.createPanditReview(input)
}