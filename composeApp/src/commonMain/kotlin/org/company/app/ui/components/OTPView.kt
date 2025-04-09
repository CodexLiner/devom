package org.company.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.company.app.theme.grey_color
import org.company.app.theme.primary_color

@Composable
fun OtpView(
    otpLength: Int = 4,
    modifier: Modifier = Modifier,
    boxSize: Dp = 70.dp,
    boxColor: Color = Color.White,
    borderColor: Color = grey_color,
    selectedBorderColor: Color = primary_color ,
    cornerRadius: Dp = 24.dp,
    itemSpacing: Dp = 24.dp,
    textColor: Color = Color.Black,
    textSize: TextUnit = 24.sp,
    onOtpEntered: (String) -> Unit
) {
    var otp by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    val interactionSource = remember { MutableInteractionSource() }

    BasicTextField(
        value = otp,
        onValueChange = { newValue ->
            if (newValue.length <= otpLength && newValue.all { it.isDigit() }) {
                otp = newValue
                if (otp.length == otpLength) {
                    onOtpEntered(otp)
                }
            }
        },
        interactionSource = interactionSource,
        cursorBrush = SolidColor(Color.Transparent),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        textStyle = TextStyle(color = Color.Transparent),
        modifier = modifier
            .fillMaxWidth()
            .focusRequester(focusRequester)
            .clickable(indication = null, interactionSource = interactionSource) {
                focusRequester.requestFocus()
            }
            .wrapContentHeight(),
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(itemSpacing),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    repeat(otpLength) { index ->
                        Box(
                            modifier = Modifier
                                .size(boxSize)
                                .background(boxColor, shape = RoundedCornerShape(cornerRadius))
                                .border(
                                    1.dp,
                                    if (otp.length == index) selectedBorderColor else borderColor,
                                    shape = RoundedCornerShape(cornerRadius)
                                )
                                .wrapContentSize(Alignment.Center)
                        ) {
                            Text(
                                text = otp.getOrNull(index)?.toString() ?: "",
                                style = TextStyle(fontSize = textSize, color = textColor, textAlign = TextAlign.Center)
                            )
                        }
                    }
                }
                innerTextField()
            }
        }
    )

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}
