package com.devom.domain.category

import com.devom.data.repository.category.CategoryRepository
import com.devom.data.repository.category.CategoryRepositoryImpl

class RemoveCategoryUseCase {
    private val categoryRepository: CategoryRepository = CategoryRepositoryImpl()
    suspend operator fun invoke(input: String) = categoryRepository.removeCategory(input)
}