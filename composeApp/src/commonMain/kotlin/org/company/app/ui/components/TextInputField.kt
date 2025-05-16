package org.company.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.company.app.theme.bg_color
import org.company.app.theme.input_color
import org.company.app.theme.text_style_lead_text
@Composable
fun TextInputField(
    initialValue: String = "",
    modifier: Modifier = Modifier.fillMaxWidth(),
    backgroundColor: Color = bg_color,
    placeholderColor: Color = input_color,
    cornerRadius: Dp = 12.dp,
    colors: TextFieldColors = TextFieldDefaults.colors(
        focusedContainerColor = backgroundColor,
        unfocusedContainerColor = backgroundColor,
        disabledContainerColor = backgroundColor,
        errorContainerColor = backgroundColor,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent,
        errorIndicatorColor = Color.Transparent,
    ),
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    placeholder: String = "Enter phone number",
    onValueChange: (String) -> Unit = {}
) {
    var input by remember { mutableStateOf(initialValue) }

    OutlinedTextField(
        value = input,
        onValueChange = {
            input = it
            onValueChange(it)
        },
        label = {
            Text(
                text = placeholder,
                style = text_style_lead_text,
                color = placeholderColor,
                modifier = Modifier.background(Color.Transparent)
            )
        },
        singleLine = true,
        shape = RoundedCornerShape(cornerRadius),
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        colors = colors,
        modifier = modifier.fillMaxWidth()
    )
}
