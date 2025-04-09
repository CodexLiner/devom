package org.company.app.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import multiplatform_app.composeapp.generated.resources.Res
import multiplatform_app.composeapp.generated.resources.ic_left
import org.jetbrains.compose.resources.painterResource

@Composable
fun BackButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    icon: Painter = painterResource(Res.drawable.ic_left),
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.Start
    ) {
        IconButton(
            onClick = onClick, modifier = Modifier.wrapContentSize()
        ) {
            Image(
                painter = icon, contentDescription = "Back", modifier = Modifier.size(24.dp)
            )
        }

        Box(
            modifier = Modifier.weight(1f)
        ) {
            content()
        }
    }
}

