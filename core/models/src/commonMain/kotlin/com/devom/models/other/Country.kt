package com.devom.models.other

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Country(
    @SerialName("name") val name: String = "",
    @SerialName("isoCode") val isoCode: String = "",
    @SerialName("flag") val flag: String = "",
    @SerialName("phonecode") val phoneCode: String = "",
    @SerialName("currency") val currency: String = "",
)

data class State(
    @SerialName("name") val name: String = "",
    @SerialName("isoCode") val isoCode: String = "",
    @SerialName("countryCode") val countryCode: String = "",
)

data class City(
    @SerialName("name") val name: String = "",
    @SerialName("isoCode") val isoCode: String = "",
    @SerialName("countryCode") val stateCode: String = "",
)