package com.devom.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.devom.app.ui.screens.HomeScreen
import com.devom.app.ui.screens.booking.BookingScreen
import com.devom.app.ui.screens.profile.EditProfileScreen
import com.devom.app.ui.screens.profile.ProfileScreen

@Composable
fun NavigationHost(navHostController: NavHostController, startDestination: String) {

    NavHost(navController = navHostController, startDestination = startDestination) {
        composable(Screens.Home.path) {
            HomeScreen()
        }

        composable(Screens.Bookings.path) {
            BookingScreen(navHostController)
        }
        composable(Screens.Profile.path) {
            ProfileScreen(navHostController)
        }

        composable(Screens.EditProfile.path) {
            EditProfileScreen()
        }
    }
}