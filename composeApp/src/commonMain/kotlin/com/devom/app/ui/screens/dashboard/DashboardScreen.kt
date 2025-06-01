package com.devom.app.ui.screens.dashboard

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.devom.app.theme.backgroundColor
import com.devom.app.ui.components.BottomMenuBar
import com.devom.app.ui.components.BottomNavigationScreen
import com.devom.app.ui.screens.HomeScreen
import com.devom.app.ui.screens.addslot.CreateSlotScreen
import com.devom.app.ui.screens.booking.BookingScreen
import com.devom.app.ui.screens.profile.ProfileScreen
import pandijtapp.composeapp.generated.resources.Res
import pandijtapp.composeapp.generated.resources.ic_nav_add
import pandijtapp.composeapp.generated.resources.ic_nav_bookings
import pandijtapp.composeapp.generated.resources.ic_nav_home
import pandijtapp.composeapp.generated.resources.ic_nav_profile
import pandijtapp.composeapp.generated.resources.ic_nav_wallet

@Composable
fun DashboardScreen(appNavHostController: NavHostController) {
    val viewModel = viewModel { DashboardViewModel() }
    var selectedTab = viewModel.selectedTab.collectAsState().value

    val screens = listOf(
        BottomNavigationScreen("home", "Home", Res.drawable.ic_nav_home, false),
        BottomNavigationScreen("bookings", "Bookings", Res.drawable.ic_nav_bookings, false),
        BottomNavigationScreen("add", "Add", Res.drawable.ic_nav_add, false),
        BottomNavigationScreen("wallet", "Wallet", Res.drawable.ic_nav_wallet, false),
        BottomNavigationScreen("profile", "Profile", Res.drawable.ic_nav_profile, false),
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Crossfade(
            targetState = selectedTab,
            modifier = Modifier.fillMaxSize().background(backgroundColor)
        ) { tab ->
            when (tab) {
                0 -> HomeScreen(navHostController = appNavHostController)
                1 -> BookingScreen(navHostController = appNavHostController)
                2 -> CreateSlotScreen(navController = appNavHostController)
                4 -> ProfileScreen(navHostController = appNavHostController)
                else -> HomeScreen(navHostController = appNavHostController)
            }
        }
        Box(modifier = Modifier.systemBarsPadding().fillMaxSize() , contentAlignment = Alignment.BottomCenter) {
            BottomMenuBar(
                screens = screens,
                selectedIndex = selectedTab,
                onNavigateTo = { viewModel.onTabSelected(it) },
            )
        }
    }
}
