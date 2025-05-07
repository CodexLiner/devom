package org.company.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.company.app.ui.screens.login.LoginScreen
import org.company.app.ui.screens.register.VerifyOtpScreen
import org.company.app.ui.screens.signup.DocumentUploadScreen
import org.company.app.ui.screens.signup.MainScreen
import org.company.app.ui.screens.signup.SignupSuccessScreen

@Composable
fun AuthNavHost(onLoginSuccess: () -> Unit) {
    val navController = rememberNavController()

    NavHost(
        navController = navController, startDestination = Screens.Login.path
    ) {
        composable(Screens.Login.path) {
            LoginScreen(navController)
        }

        composable(
            route = Screens.OtpScreen.path.plus("/{mobileNumber}"), arguments = listOf(
                navArgument("mobileNumber") { type = NavType.StringType })
        ) {
            val mobileNumber = it.arguments?.getString("mobileNumber")
            VerifyOtpScreen(navController = navController , mobileNumber = mobileNumber)
        }
        composable(Screens.SignUpSuccess.path) {
            SignupSuccessScreen(navHostController = navController) {
                onLoginSuccess()
            }
        }
        composable(Screens.Register.path) {
            MainScreen(navController)
        }
        composable(Screens.Document.path) {
            DocumentUploadScreen(navController)
        }
    }
}
