package org.company.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.devom.Project
import com.devom.models.auth.CreateUserRequest
import com.devom.network.NetworkClient
import com.devom.utils.Application.loaderState
import org.company.app.theme.AppTheme
import org.company.app.ui.components.AppContainer
import org.company.app.ui.components.ProgressLoader
import org.company.app.ui.components.ShowSnackBar
import org.company.app.ui.navigation.AuthNavHost
import org.company.app.ui.providers.LoadingCompositionProvider
import org.company.app.ui.screens.DashboardScreen


@Composable
internal fun App() = AppTheme {

    var isLoggedIn by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        NetworkClient.configure {
            baseUrl = "https://api.devom.co.in/"
            setTokens(
                access = "a009adbb10410e20296ed3dd0ba6aa7f2465d879d3788178f200a6ddcf4e13af",
                refresh = "a64fcd3ca04cf5cce0757556e12e62f041d03de86d949c7f0f357c6979ea68a3"
            )
            addHeaders {
                append("uuid", "3c9346b6-7bea-4a47-8da7-dfbebd6477a1")
            }
        }
    }

    LoadingCompositionProvider(state = loaderState.collectAsState().value) {
        AppContainer {
            Scaffold(snackbarHost = {
                ShowSnackBar()
            }, content = {
                Box(modifier = Modifier.fillMaxSize()) {
                    if (isLoggedIn) DashboardScreen() else AuthNavHost(onLoginSuccess = {
                        isLoggedIn = false
                    })
                    ProgressLoader()
                }
            })
        }
    }
}
