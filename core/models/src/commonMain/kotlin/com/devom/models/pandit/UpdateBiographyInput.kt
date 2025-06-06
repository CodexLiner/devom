package com.devom.models.pandit

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateBiographyInput(
    @SerialName("user_id")
    val userId: Int = 0,

    @SerialName("qualifications")
    val qualifications: String = "",

    @SerialName("specialty")
    var specialty: String = "",

    @SerialName("languages")
    var languages: String = "",

    @SerialName("experience_years")
    val experienceYears: String = "",

    @SerialName("services_offered")
    val servicesOffered: String = ""
)
