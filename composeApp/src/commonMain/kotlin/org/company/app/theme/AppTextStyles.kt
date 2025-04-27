package org.company.app.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

internal val text_style_h5 = TextStyle.Default.copy(
    fontWeight = FontWeight.W600,
    fontSize = 18.sp,
    lineHeight = 22.sp,
    letterSpacing = 0.sp,
    color = grey_color
)

internal val text_style_h2 = TextStyle.Default.copy(
    fontWeight = FontWeight.W700,
    fontSize = 28.sp,
    lineHeight = 36.sp,
    letterSpacing = 0.sp,
    color = black_color
)

internal val text_style_lead_text = TextStyle.Default.copy(
    fontWeight = FontWeight.W500,
    fontSize = 16.sp,
    lineHeight = 24.sp,
    letterSpacing = 0.sp,
    color = grey_color
)

internal val text_style_lead_body_1 = TextStyle.Default.copy(
    fontWeight = FontWeight.W400,
    fontSize = 14.sp,
    lineHeight = 18.sp,
    letterSpacing = 0.sp,
)