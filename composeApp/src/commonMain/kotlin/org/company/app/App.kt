package org.company.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.company.app.theme.AppTheme
import org.company.app.ui.navigation.AuthNavHost
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
