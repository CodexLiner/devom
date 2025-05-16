package com.devom.data.server.endpoints

sealed class AuthEndpoints(val path: String) {
    object SignUp : AuthEndpoints("/users/signup")
    object LoginWithOtp : AuthEndpoints("/users/dologin")
    object GenerateOtp : AuthEndpoints("/users/generate_otp")
    object GetUser : AuthEndpoints("/app_users")
    object GetUserProfile : AuthEndpoints("/users")
}

sealed class PoojaEndPoints(val path: String) {
    object GetPooja : AuthEndpoints("/pooja")
    object UpdatePooja : AuthEndpoints("/pooja")
    object CreatePoojaItemMapping : AuthEndpoints("/pooja/assign_items")
    object CreatePooja : AuthEndpoints("/pooja")
    object RemovePooja : AuthEndpoints("/pooja")
}

sealed class PoojaItemEndPoints(val path: String) {
    object GetPoojaItems : AuthEndpoints("/poojaitem")
    object CreatePoojaItem : AuthEndpoints("/poojaitem")
    object RemovePoojaItem : AuthEndpoints("/poojaitem")
    object UpdatePoojaItem : AuthEndpoints("/poojaitem")
}

sealed class PanditSlotsEndpoints(val path: String) {
    object GetAvailableSlots : AuthEndpoints("/pandit/slots")
    object CreatePanditSlot : AuthEndpoints("/pandit/slots")
    object BookPanditSlot : AuthEndpoints("/pandit/book-slot")
    object GetBookings : AuthEndpoints("/pandit/booking")
}

sealed class CategoryEndpoints(val path: String) {
    object GetCategories : AuthEndpoints("/category")
    object UpdateCategory : AuthEndpoints("/category")
    object CreateCategory : AuthEndpoints("/category")
}