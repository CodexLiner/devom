package org.company.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import co.touchlab.kermit.Logger
import com.devom.network.NetworkClient
import com.devom.utils.Application
import com.devom.utils.Application.loaderState
import com.devom.utils.Application.loginState
import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import org.company.app.theme.AppTheme
import org.company.app.ui.components.AppContainer
import org.company.app.ui.components.ProgressLoader
import org.company.app.ui.components.ShowSnackBar
import org.company.app.ui.navigation.AuthNavHost
import org.company.app.ui.providers.LoadingCompositionProvider
import org.company.app.ui.screens.DashboardScreen

val settings = Settings()

@Composable
internal fun App() = AppTheme {
    var accessKey = settings.get<String>(ACCESS_TOKEN_KEY)
    var refreshToken = settings.get<String>(REFRESH_TOKEN_KEY)
    var uuid = settings.get<String>(UUID_KEY)
    val isLoggedIn by loginState.collectAsState()

    LaunchedEffect(isLoggedIn) {
        Logger.d("KERMIT_LOGOUT ${loaderState.value}")
        if (isLoggedIn.not()) {
            settings.remove(ACCESS_TOKEN_KEY)
            settings.remove(REFRESH_TOKEN_KEY)
        } else {
            accessKey = settings.get<String>(ACCESS_TOKEN_KEY)
            refreshToken = settings.get<String>(REFRESH_TOKEN_KEY)
            uuid = settings.get<String>(UUID_KEY)
        }
    }

    LaunchedEffect(isLoggedIn) {
        Application.isLoggedIn(accessKey?.isNotEmpty() == true && refreshToken?.isNotEmpty() == true && uuid?.isNotEmpty() == true)
        NetworkClient.configure {
            baseUrl = "https://api.devom.co.in/"
            onLogOut = {
                Logger.d("ON_LOGOUT") {
                    "user has been logged out"
                }
                Application.isLoggedIn(false)

            }
            setTokens(
                access = accessKey.orEmpty(),
                refresh = refreshToken.orEmpty()
            )
            addHeaders {
                append(UUID_KEY, uuid.orEmpty())
            }
        }
    }

    LoadingCompositionProvider(state = loaderState.collectAsState().value) {
        AppContainer {
            Scaffold(snackbarHost = {
                ShowSnackBar()
            }, content = {
                Box(modifier = Modifier.fillMaxSize()) {
                    if (isLoggedIn) DashboardScreen() else AuthNavHost()
                    ProgressLoader()
                }
            })
        }
    }
}
