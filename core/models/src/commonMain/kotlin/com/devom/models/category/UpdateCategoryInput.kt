package com.devom.models.category

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateCategoryInput(
    @SerialName("name")
    val name: String = "",

    @SerialName("slug")
    val slug: String = "",

    @SerialName("description")
    val description: String = ""
)
