package org.company.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.devom.Project
import com.devom.models.auth.CreateUserRequest
import org.company.app.theme.AppTheme
import org.company.app.ui.navigation.AuthNavHost
import org.company.app.ui.screens.DashboardScreen

@Composable
internal fun App() = AppTheme {

    var isLoggedIn by remember { mutableStateOf(false) } // Replace with actual login state from ViewModel
    LaunchedEffect(Unit) {
        Project.user.registerUserUseCase.invoke(CreateUserRequest()).collect {
            println("RESULTISCAMEAND $it")
        }
    }

    if (isLoggedIn) {
        DashboardScreen()
    } else {
        AuthNavHost(onLoginSuccess = {
            isLoggedIn = true
        })
    }
}
