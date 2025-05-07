package org.company.app.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch
import org.company.app.utils.App

@Composable
fun ShowSnackBar() {
    val snackBarState = App.toastState.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    if (snackBarState.value.isNullOrEmpty().not()) {
        Box(modifier = Modifier.fillMaxWidth()) {
            SnackbarHost(
                hostState = snackBarHostState,
                modifier = Modifier.align(Alignment.BottomCenter)
            )

            LaunchedEffect(Unit) {
                scope.launch {
                    snackBarHostState.showSnackbar(
                        message = snackBarState.value.orEmpty()
                    )
                    App.hideToast()
                }
            }
        }
    }
}