package org.company.app.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import org.company.app.ui.components.AppContainer
import org.company.app.ui.components.CustomBottomNavigationBar
import org.company.app.ui.navigation.NavigationHost
import org.company.app.ui.navigation.Screens

@Composable
fun DashboardScreen() {
    val navController = rememberNavController()
    var selectedTab by remember { mutableStateOf(0) }

    Scaffold(
        modifier = Modifier.safeDrawingPadding(),
        bottomBar = {
            CustomBottomNavigationBar(
                selectedIndex = selectedTab,
                onItemSelected = { index ->
                    selectedTab = index
                    val destination = when (index) {
                        0 -> Screens.Home.path
                        1 -> Screens.Bookings.path
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
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    NavigationHost(
                        navHostController = navController,
                        startDestination = Screens.Home.path
                    )
                }
            }
        }
    )
}
