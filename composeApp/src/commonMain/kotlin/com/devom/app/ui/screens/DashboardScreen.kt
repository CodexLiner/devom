package com.devom.app.ui.screens

import androidx.compose.animation.Crossfade
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
import com.devom.app.ui.components.AppContainer
import com.devom.app.ui.components.BottomNavigationBar
import com.devom.app.ui.screens.booking.BookingScreen
import com.devom.app.ui.screens.profile.ProfileScreen

@Composable
fun DashboardScreen(appNavHostController: NavHostController) {
    var selectedTab by remember { mutableStateOf(0) }
    Scaffold(
        modifier = Modifier,
        bottomBar = {
            BottomNavigationBar(
                selectedIndex = selectedTab,
                onItemSelected = { selectedTab = it }
            )
        },
        content = { paddingValues ->
            AppContainer {
                Box(modifier = Modifier.fillMaxSize().navigationBarsPadding()) {
                    Crossfade(targetState = selectedTab) { tab ->
                        when (tab) {
                            0 -> HomeScreen(navHostController = appNavHostController)
                            1 -> BookingScreen(navHostController = appNavHostController)
                            3 -> ProfileScreen(navHostController = appNavHostController)
                            else -> HomeScreen(navHostController = appNavHostController)
                        }
                    }
                }
            }
        }
    )
}