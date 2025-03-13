package org.company.app

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.russhwolf.settings.get
import com.russhwolf.settings.set
import kotlinx.coroutines.launch
import multiplatform_app.composeapp.generated.resources.*
import org.company.app.theme.AppTheme
import org.company.app.ui.components.AppContainer
import org.company.app.ui.components.ButtonPrimary
import org.company.app.ui.screens.login.LoginScreen
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun App() = AppTheme {
    rememberNavController()
    AppContainer(modifier = Modifier.safeDrawingPadding()) {
        Box(modifier = Modifier.fillMaxSize() , contentAlignment = Alignment.Center) {
            LoginScreen(rememberNavController())
        }
    }
}



@Composable
fun localStringResources(localKey: StringResource): String {
    return LogicKt.serverStrings.getOrElse(localKey.key) {
        stringResource(localKey)
    }
}