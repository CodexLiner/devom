package com.devom.domain.category

import com.devom.data.repository.category.CategoryRepository
import com.devom.data.repository.category.CategoryRepositoryImpl
import com.devom.models.category.CreateCategoryInput

class CreateCategoryUseCase {
    private val categoryRepository: CategoryRepository = CategoryRepositoryImpl()
    suspend fun invoke(input: CreateCategoryInput) = categoryRepository.createCategory(input)
}