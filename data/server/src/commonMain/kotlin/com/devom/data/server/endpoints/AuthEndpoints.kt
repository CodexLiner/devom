package com.devom.data.server.endpoints

sealed class AuthEndpoints(val path: String) {
    object SignUp : AuthEndpoints("/users/signup")
    object LoginWithOtp : AuthEndpoints("/users/dologin")
    object GenerateOtp : AuthEndpoints("/users/generate_otp")
    object GetUser : AuthEndpoints("/app_users")
}

sealed class PoojaEndPoints(val path: String) {
    object GetPooja : AuthEndpoints("/pooja")
}