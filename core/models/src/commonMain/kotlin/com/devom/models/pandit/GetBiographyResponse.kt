package com.devom.models.pandit

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetBiographyResponse(
    @SerialName("profile_id")
    val profileId: Int = 0,
    
    @SerialName("user_id")
    val userId: Int = 0,
    
    @SerialName("qualifications")
    val qualifications: String = "",
    
    @SerialName("specialty")
    val specialty: String = "",
    
    @SerialName("languages")
    val languages: String = "",
    
    @SerialName("experience_years")
    val experienceYears: String = "",
    
    @SerialName("services_offered")
    val servicesOffered: String = "",
    
    @SerialName("ratings")
    val ratings: String? = null,
    
    @SerialName("status")
    val status: String = "",
    
    @SerialName("is_deleted")
    val isDeleted: Int = 0,
    
    @SerialName("created_at")
    val createdAt: String = "",
    
    @SerialName("updated_at")
    val updatedAt: String = "",
    
    @SerialName("media")
    val media: List<Media> = emptyList()
)

@Serializable
data class Media(
    @SerialName("document_url")
    val documentUrl: String = "",
    
    @SerialName("document_type")
    val documentType: String = "",
    
    @SerialName("document_id")
    val documentId: Int = 0
)