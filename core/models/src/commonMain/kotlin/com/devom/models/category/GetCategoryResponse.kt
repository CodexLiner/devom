package com.devom.models.category

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetCategoryResponse(
    @SerialName("id") val id: Int,

    @SerialName("name") val name: String,

    @SerialName("slug") val slug: String,

    @SerialName("description") val description: String,

    @SerialName("image") val image: String,

    @SerialName("parent_id") val parentId: Int?,

    @SerialName("status") val status: Int,

    @SerialName("created_at") val createdAt: String,

    @SerialName("updated_at") val updatedAt: String,
)