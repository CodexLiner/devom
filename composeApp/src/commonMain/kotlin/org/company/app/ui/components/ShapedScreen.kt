package org.company.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.company.app.theme.black_color
import org.company.app.theme.white_color

@Composable
fun ShapedScreen(
    headerContent: @Composable () -> Unit, mainContent: (@Composable () -> Unit)
) {
    Column(modifier = Modifier.fillMaxSize().background(color = black_color)) {
        Box(modifier = Modifier.fillMaxWidth().weight(1f)) {
            headerContent()
        }
        Box(
            modifier = Modifier.fillMaxSize().weight(3f).background(
                color = white_color, shape = RoundedCornerShape(
                    topStart = 32.dp, topEnd = 32.dp
                )
            )
        ) {
            mainContent()
        }

    }
}