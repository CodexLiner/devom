package com.devom.domain.category

import com.devom.data.repository.category.CategoryRepository
import com.devom.data.repository.category.CategoryRepositoryImpl

class GetCategoriesUseCase {
    private val categoryRepository: CategoryRepository = CategoryRepositoryImpl()
    suspend fun invoke() = categoryRepository.getCategories()

}