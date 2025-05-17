package com.devom.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.devom.app.theme.black_color
import com.devom.app.theme.white_color
@Composable
fun ShapedScreen(
    headerContent: @Composable () -> Unit,
    mainContent: @Composable () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().background(color = black_color).statusBarsPadding()) {
        Box(modifier = Modifier.fillMaxWidth().wrapContentHeight()) {
            headerContent()
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f, fill = true)
                .background(
                    color = white_color,
                    shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
                )
        ) {
            mainContent()
        }
    }
}
