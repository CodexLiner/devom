package org.company.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.company.app.ui.screens.HomeScreen
import org.company.app.ui.screens.booking.BookingScreen
import org.company.app.ui.screens.profile.ProfileScreen

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
    }
}