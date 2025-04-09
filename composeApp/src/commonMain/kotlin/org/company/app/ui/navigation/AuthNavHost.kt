package org.company.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.company.app.ui.screens.login.LoginScreen
import org.company.app.ui.screens.register.RegisterScreen
import org.company.app.ui.screens.signup.DocumentUploadScreen
import org.company.app.ui.screens.signup.GeneralScreen
import org.company.app.ui.screens.signup.SignupSuccessScreen

@Composable
fun AuthNavHost(onLoginSuccess: () -> Unit) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.Login.path
    ) {
        composable(Screens.Login.path) {
            LoginScreen(navController)
        }
        composable(Screens.OtpScreen.path) {
            RegisterScreen(navController = navController)
        }
        composable(Screens.SignUpSuccess.path) {
            SignupSuccessScreen(navHostController = navController) {
                onLoginSuccess()
            }
        }
        composable(Screens.Register.path){
            GeneralScreen(navController)
        }
        composable(Screens.Document.path) {
            DocumentUploadScreen(navController)
        }
    }
}
