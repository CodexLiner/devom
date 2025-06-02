package com.devom.app.ui.navigation

/**
 * Sealed class representing the different screens in the application.
 * Each screen is defined as a data object inheriting from this class.
 * This class provides a type-safe way to navigate between screens
 * by using the screen's path as a unique identifier.
 *
 * @property path The unique string identifier (path) for the screen.
 */
sealed class Screens(val path: String) {
    data object Login : Screens(path = "login")
    data object Home : Screens(path = "home")
    data object BookingDetails : Screens(path = "details")
    data object Register : Screens(path = "register")
    data object Document : Screens(path = "document")
    data object SignUpSuccess : Screens(path = "signup_success")
    data object Bookings : Screens(path = "bookings")
    data object OtpScreen : Screens(path = "otpScreen")
    data object Profile : Screens(path = "profile")
    data object EditProfile : Screens(path = "edit_profile")
    data object Dashboard : Screens(path = "dashboard")
    data object CreateSlot : Screens(path = "create_slot")
    data object Notifications : Screens(path = "notifications")
    data object UploadDocument : Screens(path = "upload_document")
    data object UploadDocumentSuccess : Screens(path = "upload_document_success")

}