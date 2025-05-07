package org.company.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.company.app.ui.providers.LocalLoaderState

@Composable
fun ProgressLoader() {
    val isLoading = LocalLoaderState.current
    if (isLoading) {
        Box(
            modifier = Modifier.fillMaxSize().fillMaxSize()
                .clickable(interactionSource = null, indication = null) { }
                .background(Color.Black.copy(alpha = 0.5f)), contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}
