package com.devom.models.pooja

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetPoojaResponse(
    @SerialName("id") val id: Int = 0,

    @SerialName("name") val name: String = "",

    @SerialName("description") val description: String = "",

    @SerialName("category_id") val categoryId: Int = 0,

    @SerialName("total_time") val totalTime: String = "",

    @SerialName("image") val image: String = "",

    @SerialName("price") val price: String = "",

    @SerialName("status") val status: Int = 0,

    @SerialName("created_at") val createdAt: String = "",

    @SerialName("updated_at") val updatedAt: String = ""
)