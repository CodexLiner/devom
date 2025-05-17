package com.devom.app.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.devom.app.ui.components.AppContainer
import com.devom.app.ui.components.BottomNavigationBar
import com.devom.app.ui.navigation.Screens
import com.devom.app.ui.screens.booking.BookingScreen
import com.devom.app.ui.screens.profile.ProfileScreen

@Composable
fun DashboardScreen(appNavHostController: NavHostController) {
    val navController = rememberNavController()
    var selectedTab by remember { mutableStateOf(0) }

    Scaffold(
        modifier = Modifier,
        bottomBar = {
            BottomNavigationBar(
                selectedIndex = selectedTab,
                onItemSelected = { index ->
                    selectedTab = index
                    val destination = when (index) {
                        0 -> Screens.Home.path
                        1 -> Screens.Bookings.path
                        3 -> Screens.Profile.path
                        else -> Screens.Home.path
                    }
                    navController.navigate(destination) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        },
        content = { paddingValues ->
            AppContainer {
                Box(modifier = Modifier.fillMaxSize().navigationBarsPadding()) {
                    DashBoardNavigationHost(
                        navHostController = navController,
                        startDestination = Screens.Profile.path,
                        appNavHostController = appNavHostController
                    )
                }
            }
        }
    )
}


@Composable
private fun DashBoardNavigationHost(
    navHostController: NavHostController,
    appNavHostController: NavHostController,
    startDestination: String,
) {
    NavHost(navController = navHostController, startDestination = startDestination) {
        composable(Screens.Home.path) {
            HomeScreen(navHostController = appNavHostController)
        }
        composable(Screens.Bookings.path) {
            BookingScreen(appNavHostController)
        }
        composable(Screens.Profile.path) {
            ProfileScreen(appNavHostController)
        }
    }
}
