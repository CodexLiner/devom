package org.company.app

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
import org.company.app.theme.AppTheme
import org.company.app.ui.components.AppContainer
import org.company.app.ui.components.CustomBottomNavigationBar
import org.company.app.ui.navigation.AuthNavHost
import org.company.app.ui.navigation.NavigationHost
import org.company.app.ui.navigation.Screens
import org.company.app.ui.screens.DashboardScreen

@Composable
internal fun App() = AppTheme {
    var isLoggedIn by remember { mutableStateOf(false) } // Replace with actual login state from ViewModel

    if (isLoggedIn) {
        DashboardScreen()
    } else {
        AuthNavHost(onLoginSuccess = {
            isLoggedIn = true
        })
    }
}
