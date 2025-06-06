package com.devom.models.pandit

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MapPanditPoojaItemInput(
    @SerialName("pandit_id")
    val panditId: Int = 0,

    @SerialName("pooja_id")
    val poojaId: Int = 0,

    @SerialName("without_item_price")
    val withoutItemPrice: String = "",

    @SerialName("with_item_price")
    val withItemPrice: String = "",

    @SerialName("estimated_time_minutes")
    val estimatedTimeMinutes: String = "",

    @SerialName("notes")
    val notes: String = "",
)