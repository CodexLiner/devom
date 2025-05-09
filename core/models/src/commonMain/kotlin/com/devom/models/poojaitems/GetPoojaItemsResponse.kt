package com.devom.models.poojaitems

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetPoojaItemsResponse(
    @SerialName("id") val id: Int = 0,
    @SerialName("name") val name: String = "",
    @SerialName("description") val description: String = "",
    @SerialName("image") val image: String = "",
    @SerialName("status") val status: Int = 0,
    @SerialName("created_at") val createdAt: String = "",
    @SerialName("updated_at") val updatedAt: String = ""
)
