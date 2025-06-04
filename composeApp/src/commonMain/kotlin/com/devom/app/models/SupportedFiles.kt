package com.devom.app.models

enum class SupportedFiles(
    val document: String,
    val type: String,
    val mimeTypes: List<String>
) {
    AADHAAR_CARD("Aadhaar Card", "aadhaar_card", listOf("application/pdf", "image/jpeg", "image/png")),
    PAN_CARD("Pan Card", "pan_card", listOf("application/pdf", "image/jpeg")),
    CERTIFICATE("Certificate", "certificate", listOf("application/pdf")),
    OTHER("Other", "other", listOf("*/*")); // fallback
}
