package com.devom.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.devom.app.ui.screens.login.LoginScreen
import com.devom.app.ui.navigation.Screens.BookingDetails
import com.devom.app.ui.screens.DashboardScreen
import com.devom.app.ui.screens.booking.details.BookingDetailScreen
import com.devom.app.ui.screens.otpscreen.VerifyOtpScreen
import com.devom.app.ui.screens.profile.EditProfileScreen
import com.devom.app.ui.screens.signup.DocumentUploadScreen
import com.devom.app.ui.screens.signup.RegisterMainScreen
import com.devom.app.ui.screens.signup.SignupSuccessScreen

@Composable
fun NavigationHost(startDestination: String = Screens.Login.path, navController: NavHostController) {
    NavHost(
        navController = navController, startDestination = startDestination
    ) {
        composable(Screens.Login.path) {
            LoginScreen(navController)
        }
        composable(
            route = Screens.OtpScreen.path.plus("/{mobileNumber}"),
            arguments = listOf(navArgument("mobileNumber") { type = NavType.StringType })) {
            VerifyOtpScreen(navController = navController, mobileNumber = it.arguments?.getString("mobileNumber"))
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
            BookingDetailScreen(navController)
        }
        composable(Screens.Dashboard.path) {
            DashboardScreen(navController)
        }
        composable(Screens.EditProfile.path) {
            EditProfileScreen()
        }
    }
}
