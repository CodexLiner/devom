package com.devom.models.category

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetCategoryResponse(
    @SerialName("id") val id: Int = 0,
    @SerialName("name") val name: String = "",
    @SerialName("slug") val slug: String = "",
    @SerialName("description") val description: String = "",
    @SerialName("image") val image: String = "",
    @SerialName("parent_id") val parentId: Int? = null,
    @SerialName("status") val status: Int = 0,
    @SerialName("created_at") val createdAt: String = "",
    @SerialName("updated_at") val updatedAt: String = ""
)
