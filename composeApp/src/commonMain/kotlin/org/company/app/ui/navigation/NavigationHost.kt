package org.company.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.company.app.ui.screens.HomeScreen
import org.company.app.ui.screens.LoginScreen

@Composable
fun NavigationHost(navHostController: NavHostController, startDestination: String) {

    NavHost(navController = navHostController, startDestination = startDestination) {
        composable(Screens.Home.path) {
            HomeScreen()
        }
        composable(Screens.Login.path) {
            LoginScreen()
        }
    }
}