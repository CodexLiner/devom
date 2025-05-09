package com.devom.category

import com.devom.domain.category.CreateCategoryUseCase
import com.devom.domain.category.GetCategoriesUseCase
import com.devom.domain.category.RemoveCategoryUseCase
import com.devom.domain.category.UpdateCategoryUseCase

class Category {
    private val createCategoryUseCase by lazy {
        CreateCategoryUseCase()
    }
    private val getCategoriesUseCase by lazy {
        GetCategoriesUseCase()
    }

    private val updateCategoryUseCase by lazy {
        UpdateCategoryUseCase()
    }
    private val removeCategoryUseCase by lazy {
        RemoveCategoryUseCase()
    }

}