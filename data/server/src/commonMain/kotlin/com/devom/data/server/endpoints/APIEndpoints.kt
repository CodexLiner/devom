package com.devom.data.server.endpoints
object AuthEndpoints {
    const val SignUp = "/users/signup"
    const val LoginWithOtp = "/users/dologin"
    const val UpdateUserProfile = "/users"
    const val GenerateOtp = "/users/generate_otp"
    const val GetUser = "/app_users"
    const val GetUserProfile = "/users"
}

object PoojaEndpoints {
    const val GetPooja = "/pooja"
    const val UpdatePooja = "/pooja"
    const val CreatePoojaItemMapping = "/pooja/assign_items"
    const val CreatePooja = "/pooja"
    const val RemovePooja = "/pooja"
}

object PoojaItemEndpoints {
    const val GetPoojaItems = "/poojaitem"
    const val CreatePoojaItem = "/poojaitem"
    const val RemovePoojaItem = "/poojaitem"
    const val UpdatePoojaItem = "/poojaitem"
}

object PanditSlotsEndpoints {
    const val GetAvailableSlots = "/pandit/slots"
    const val CreatePanditSlot = "/pandit/slots"
    const val BookPanditSlot = "/pandit/book-slot"
    const val GetBookings = "/pandit/booking"
    const val UpdateBookingStatus = "/pandit/booking_status_change"
    const val GetBookingById = "/pandit/booking"
    const val RemovePanditSlot = "/pandit/slot"

}

object CategoryEndpoints {
    const val GetCategories = "/category"
    const val UpdateCategory = "/category"
    const val CreateCategory = "/category"
}

object DocumentEndpoints {
    const val GetDocuments = "/document"
    const val UpdateDocument = "/document/verify"
    const val RemoveDocument = "/document"
    const val CreateDocument = "/document/upload"
}

object PanditEndpoints {
    const val GetReviews = "pandit_profile/review"
    const val CreateReview = "pandit_profile/review"
    const val UpdateBiography = "pandit_profile"
    const val GetBiography = "pandit_profile/get_biography"
    const val MapPanditPoojaItem = "pandit_profile/map_pandit_pooja"
    const val RemovePanditPoojaMapping = "pandit_profile/pooja"
    const val GetPanditPooja = "pandit_profile"
}

object  PaymentEndpoints {
    const val GetWalletBalance = "/payment/get_wallet"
    const val GetTransactions = "/payment"
}
