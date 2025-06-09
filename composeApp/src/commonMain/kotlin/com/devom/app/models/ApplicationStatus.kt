package com.devom.app.models

enum class ApplicationStatus(val status: String) {
    PENDING("pending"),
    ACCEPTED("accepted"),
    REJECTED("rejected"),
    CANCELLED("cancelled"),
    COMPLETED("completed"),
    CONFIRMED("confirmed"),
    VERIFIED("verified"),
}