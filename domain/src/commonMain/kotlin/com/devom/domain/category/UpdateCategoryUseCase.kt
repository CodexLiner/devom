package com.devom.domain.category

import com.devom.data.repository.category.CategoryRepository
import com.devom.data.repository.category.CategoryRepositoryImpl
import com.devom.models.category.UpdateCategoryInput

class UpdateCategoryUseCase {
    private val categoryRepository: CategoryRepository = CategoryRepositoryImpl()
    suspend operator fun invoke(id: String, input: UpdateCategoryInput) = categoryRepository.updateCategory(id, input)

}