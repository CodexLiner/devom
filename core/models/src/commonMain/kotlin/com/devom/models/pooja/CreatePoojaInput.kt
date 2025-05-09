package com.devom.models.pooja

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreatePoojaInput(
    @SerialName("name") val name: String = "", 
    @SerialName("description") val description: String = ""
)
