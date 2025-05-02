package endpoints

sealed class AuthEndpoints(val path: String) {
    object SignUp : AuthEndpoints("/users/signup")
}