package com.devom.data.repository.category

import com.devom.data.server.category.CategoryRemoteDataSource
import com.devom.data.server.category.CategoryRemoteDataSourceImpl
import com.devom.models.category.CreateCategoryInput
import com.devom.models.category.GetCategoryResponse
import com.devom.models.category.UpdateCategoryInput
import com.devom.utils.flow.apiFlow
import com.devom.utils.network.ResponseResult
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    suspend fun getCategories(): Flow<ResponseResult<List<GetCategoryResponse>>>
    suspend fun removeCategory(categoryId: String): Flow<ResponseResult<String>>
    suspend fun createCategory(input: CreateCategoryInput): Flow<ResponseResult<String>>
    suspend fun updateCategory(id: String, input: UpdateCategoryInput): Flow<ResponseResult<String>>
}

class CategoryRepositoryImpl() : CategoryRepository {
    private val categoryRemoteDataSource: CategoryRemoteDataSource = CategoryRemoteDataSourceImpl()

    override suspend fun getCategories(): Flow<ResponseResult<List<GetCategoryResponse>>> = apiFlow {
        categoryRemoteDataSource.getCategories()
    }

    override suspend fun removeCategory(categoryId: String): Flow<ResponseResult<String>> =  apiFlow {
        categoryRemoteDataSource.removeCategory(categoryId)
    }

    override suspend fun createCategory(input: CreateCategoryInput): Flow<ResponseResult<String>> = apiFlow {
        categoryRemoteDataSource.createCategory(input)
    }

    override suspend fun updateCategory(id: String, input: UpdateCategoryInput, ): Flow<ResponseResult<String>> = apiFlow {
        categoryRemoteDataSource.updateCategory(id, input)
    }

}