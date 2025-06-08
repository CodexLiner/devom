package com.devom.app.models

enum class SupportedFiles(
    val document: String,
    val type: String,
    val mimeTypes: List<String>
) {
    AADHAAR_CARD("Aadhaar Card", "aadhaar_card", listOf("application/pdf", "image/jpeg", "image/png")),
    PAN_CARD("Pan Card", "pan_card", listOf("application/pdf", "image/jpeg")),
    CERTIFICATE("Certificate", "certificate", listOf("application/pdf")),
    IMAGE("Image", "other", listOf("image/jpeg", "image/png", "image/gif")),
    VIDEO("Video", "other", listOf("video/mp4", "video/x-matroska", "video/webm")),
    IMAGE_AND_VIDEO("Image and Video", "other", listOf("image/jpeg", "image/png", "video/mp4", "video/x-matroska")),
    VIEW("View", "view", listOf("*/*")),
    OTHER("Other", "other", listOf("*/*"));
}
