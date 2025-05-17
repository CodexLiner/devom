package com.devom.app.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import multiplatform_app.composeapp.generated.resources.Res
import multiplatform_app.composeapp.generated.resources.btn_continue
import com.devom.app.theme.orange_shadow
import com.devom.app.theme.white_color
import org.jetbrains.compose.resources.stringResource

@Composable
fun ButtonPrimary(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(12.dp),
    colors: ButtonColors = ButtonDefaults.buttonColors().copy(
        containerColor = orange_shadow,
        contentColor = white_color
    ),
    textColor: Color = white_color,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    buttonText: String = stringResource(Res.string.btn_continue),
    onClick: () -> Unit
) {

    Button(modifier = modifier, colors = colors, shape = shape, onClick = onClick) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            leadingIcon?.invoke()
            Text(text = buttonText, color = textColor)
            trailingIcon?.invoke()
        }
    }
}