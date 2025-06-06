package com.devom.models.pandit

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetPanditPoojaResponse(
    @SerialName("id")
    val id: Int = 0,

    @SerialName("pandit_id")
    val panditId: Int = 0,

    @SerialName("pooja_id")
    val poojaId: Int = 0,

    @SerialName("with_item_price")
    val withItemPrice: String = "",

    @SerialName("without_item_price")
    val withoutItemPrice: String = "",

    @SerialName("estimated_time_minutes")
    val estimatedTimeMinutes: Int = 0,

    @SerialName("notes")
    val notes: String = "",

    @SerialName("status")
    val status: String = "",

    @SerialName("created_at")
    val createdAt: String = "",

    @SerialName("updated_at")
    val updatedAt: String = "",

    @SerialName("pooja_name")
    val poojaName: String = "",

    @SerialName("description")
    val description: String = ""
)
