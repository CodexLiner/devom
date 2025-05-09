package com.devom.data.server.category

import com.devom.data.server.endpoints.CategoryEndpoints
import com.devom.models.category.CreateCategoryInput
import com.devom.models.category.GetCategoryResponse
import com.devom.models.category.UpdateCategoryInput
import com.devom.network.NetworkClient
import com.devom.network.utils.toResponseResult
import com.devom.utils.network.ResponseResult
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import kotlinx.coroutines.flow.Flow

interface CategoryRemoteDataSource {
    suspend fun getCategories(): Flow<ResponseResult<List<GetCategoryResponse>>>
    suspend fun removeCategory(categoryId: String): Flow<ResponseResult<String>>
    suspend fun createCategory(input: CreateCategoryInput): Flow<ResponseResult<String>>
    suspend fun updateCategory(id: String, input: UpdateCategoryInput): Flow<ResponseResult<String>>
}

class CategoryRemoteDataSourceImpl() : CategoryRemoteDataSource {
    private val ktorClient = NetworkClient.ktorClient

    override suspend fun getCategories(): Flow<ResponseResult<List<GetCategoryResponse>>> =
        ktorClient.get(CategoryEndpoints.GetCategories.path).toResponseResult()

    override suspend fun removeCategory(categoryId: String): Flow<ResponseResult<String>> =
        ktorClient.delete(CategoryEndpoints.GetCategories.path.plus("/$categoryId"))
            .toResponseResult()

    override suspend fun createCategory(
        input: CreateCategoryInput,
    ): Flow<ResponseResult<String>> = ktorClient.post(CategoryEndpoints.CreateCategory.path) { setBody(input) }.toResponseResult()

    override suspend fun updateCategory(id: String, input: UpdateCategoryInput): Flow<ResponseResult<String>> =
        ktorClient.put(CategoryEndpoints.UpdateCategory.path.plus("/$id")) { setBody(input) }.toResponseResult()

}