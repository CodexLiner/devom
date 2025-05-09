package com.devom.models.pooja

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PoojaItemMappingInput(
    @SerialName("item_id") val itemId: Int = 0,
    @SerialName("quantity") val quantity: Int = 0,
    @SerialName("unit") val unit: String = ""
)