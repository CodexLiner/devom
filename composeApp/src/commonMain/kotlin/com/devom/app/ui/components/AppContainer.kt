package com.devom.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.devom.app.theme.backgroundColor

@Composable
fun AppContainer(modifier: Modifier = Modifier.background(backgroundColor) , content: @Composable () -> Unit) {
    Box(modifier = Modifier) {
        content()
    }
}