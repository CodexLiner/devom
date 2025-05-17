package com.devom.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.devom.app.ui.screens.login.LoginScreen
import com.devom.app.ui.navigation.Screens.BookingDetails
import com.devom.app.ui.screens.booking.details.BookingDetailScreen
import com.devom.app.ui.screens.otpscreen.VerifyOtpScreen
import com.devom.app.ui.screens.signup.DocumentUploadScreen
import com.devom.app.ui.screens.signup.RegisterMainScreen
import com.devom.app.ui.screens.signup.SignupSuccessScreen

@Composable
fun AuthNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController, startDestination = Screens.Login.path
    ) {
        composable(Screens.Login.path) {
            LoginScreen(navController)
        }

        composable(
            route = Screens.OtpScreen.path.plus("/{mobileNumber}"), arguments = listOf(
                navArgument("mobileNumber") { type = NavType.StringType })
        ) {
            val mobileNumber = it.arguments?.getString("mobileNumber")
            VerifyOtpScreen(navController = navController , mobileNumber = mobileNumber)
        }
        composable(Screens.SignUpSuccess.path) {
            SignupSuccessScreen(navHostController = navController)
        }
        composable(Screens.Register.path) {
            RegisterMainScreen(navController)
        }
        composable(Screens.Document.path) {
            DocumentUploadScreen(navController)
        }
        composable(BookingDetails.path) {
            BookingDetailScreen(
                navController,
            )
        }
    }
}
