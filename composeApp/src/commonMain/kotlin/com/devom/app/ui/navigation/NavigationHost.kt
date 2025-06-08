package com.devom.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.devom.app.ui.navigation.Screens.Biography
import com.devom.app.ui.screens.login.LoginScreen
import com.devom.app.ui.navigation.Screens.BookingDetails
import com.devom.app.ui.navigation.Screens.Notifications
import com.devom.app.ui.navigation.Screens.ReviewsAndRatings
import com.devom.app.ui.screens.dashboard.DashboardScreen
import com.devom.app.ui.screens.addslot.CreateSlotScreen
import com.devom.app.ui.screens.biography.BiographyScreen
import com.devom.app.ui.screens.booking.details.BookingDetailScreen
import com.devom.app.ui.screens.document.UploadDocumentScreen
import com.devom.app.ui.screens.helpandsupport.HelpAndSupportScreen
import com.devom.app.ui.screens.notification.NotificationScreen
import com.devom.app.ui.screens.otpscreen.VerifyOtpScreen
import com.devom.app.ui.screens.profile.EditProfileScreen
import com.devom.app.ui.screens.referandearn.ReferAndEarnScreen
import com.devom.app.ui.screens.reviews.ReviewsAndRatingsScreen
import com.devom.app.ui.screens.rituals.RitualsScreen
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
        composable(
            route = BookingDetails.path.plus("/{booking}"),
            arguments = listOf(navArgument("booking") { type = NavType.StringType })
        ) {
            BookingDetailScreen(
                navController = navController,
                bookingId = it.arguments?.getString("booking")
            )
        }
        composable(Screens.Dashboard.path) {
            DashboardScreen(navController)
        }
        composable(Screens.EditProfile.path) {
            EditProfileScreen(navController)
        }
        composable(Screens.CreateSlot.path) {
            CreateSlotScreen(
                navController,
            )
        }
        composable(Notifications.path) {
            NotificationScreen(navController)
        }
        composable(Screens.UploadDocument.path) {
            UploadDocumentScreen(navController)
        }
        composable(ReviewsAndRatings.path) {
            ReviewsAndRatingsScreen(navController = navController)
        }
        composable(Biography.path) {
            BiographyScreen(navController = navController)
        }
        composable(Screens.Rituals.path) {
            RitualsScreen(navController = navController)
        }
        composable(Screens.HelpAndSupport.path) {
            HelpAndSupportScreen(navController = navController)
        }
        composable(Screens.ReferAndEarn.path) {
            ReferAndEarnScreen(navController = navController)
        }
    }
}
