package com.devom.app

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
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
import co.touchlab.kermit.Logger
import com.devom.network.NetworkClient
import com.devom.utils.Application.isLoggedIn
import com.devom.utils.Application.loaderState
import com.devom.utils.Application.loginState
import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.devom.app.theme.AppTheme
import com.devom.app.ui.components.AppContainer
import com.devom.app.ui.components.ProgressLoader
import com.devom.app.ui.components.ShowSnackBar
import com.devom.app.ui.navigation.AuthNavHost
import com.devom.app.ui.providers.LoadingCompositionProvider
import com.devom.app.ui.screens.DashboardScreen

val settings = Settings()

@Composable
internal fun App() = AppTheme {
    var accessKey by remember { mutableStateOf(settings.get<String>(ACCESS_TOKEN_KEY)) }
    var refreshToken by remember { mutableStateOf(settings.get<String>(ACCESS_TOKEN_KEY)) }
    var uuid by remember { mutableStateOf(settings.get<String>(UUID_KEY)) }

    val isLoggedIn by loginState.collectAsState()
    var initialized by remember { mutableStateOf(false) }

    LaunchedEffect(isLoggedIn){
        if (isLoggedIn.not()) {
            settings.remove(ACCESS_TOKEN_KEY)
            settings.remove(ACCESS_TOKEN_KEY)
            settings.remove(UUID_KEY)
        } else {
            accessKey = settings.get<String>(ACCESS_TOKEN_KEY)
            refreshToken = settings.get<String>(ACCESS_TOKEN_KEY)
            uuid = settings.get<String>(UUID_KEY)
        }
    }

    LaunchedEffect(Unit) {
        val loggedIn = accessKey?.isNotEmpty() == true && refreshToken?.isNotEmpty() == true && uuid?.isNotEmpty() == true
        isLoggedIn(loggedIn)
        NetworkClient.configure {
            setTokens(access = accessKey.orEmpty(), refresh = refreshToken.orEmpty())
            baseUrl = "https://devom-api-bold-smoke-8130.fly.dev"
            onLogOut = {
                Logger.d("ON_LOGOUT") { "user has been logged out" }
                isLoggedIn(false)
            }
            addHeaders {
                append(UUID_KEY, uuid.orEmpty())
            }
        }
        initialized = true
    }

    if (initialized) MainScreen(isLoggedIn)
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen(isLoggedIn: Boolean) {
    LoadingCompositionProvider(state = loaderState.collectAsState().value) {
        AppContainer {
            Scaffold(snackbarHost = { ShowSnackBar() }, content = {
                Box(modifier = Modifier.fillMaxSize()) {
                    AnimatedContent(
                        targetState = isLoggedIn, transitionSpec = {
                            fadeIn(animationSpec = tween(300)) with fadeOut(
                                animationSpec = tween(
                                    300
                                )
                            )
                        }, label = "Auth/Dashboard Transition"
                    ) { target ->
                        if (target) DashboardScreen() else DashboardScreen()
                    }

                    ProgressLoader()
                }
            })
        }
    }
}