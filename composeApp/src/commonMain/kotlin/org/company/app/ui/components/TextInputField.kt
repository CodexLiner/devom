package org.company.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.company.app.theme.bg_color
import org.company.app.theme.input_color
import org.company.app.theme.text_style_lead_text

@Composable
fun TextInputField(
    modifier: Modifier = Modifier.fillMaxWidth().background(color = bg_color),
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(
        focusedTextColor = input_color,
        unfocusedTextColor = input_color,
        unfocusedBorderColor = Color.Transparent,
        disabledBorderColor = Color.Transparent,
        errorBorderColor = Color.Transparent
    ),
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    placeholder: String = "Enter phone number",
    onValueChange: (String) -> Unit = {}
) {


    var input by remember { mutableStateOf("") }
    OutlinedTextField(
        placeholder = {
            Text(text = placeholder, color = input_color, style = text_style_lead_text)
        },
        singleLine = true,
        shape = RoundedCornerShape(12),
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        colors = colors,
        modifier = modifier,
        onValueChange = {
            input = it
            onValueChange(it)
        },
        value = input
    )

}