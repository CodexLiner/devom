package com.devom.models.slots

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoveAndUpdatePoojaItemRequest(
    @SerialName("add_items")
    val addItems: List<Int> = listOf(),
    @SerialName("remove_items")
    val removeItems: List<Int> = listOf(),
)