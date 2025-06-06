package com.devom.app.utils

import androidx.compose.ui.graphics.Color
import coil3.Uri
import coil3.toUri
import com.devom.app.DOCUMENT_BASE_URL
import com.devom.app.IMAGE_BASE_URL
import io.ktor.http.Url
import io.ktor.http.encodeURLPath

fun String.toColor(): Color {
    val hex = removePrefix("#")
    return Color(hex.toLong(16) or (if (hex.length == 6) 0xFF000000 else 0x00000000))
}

fun String?.toDevomImage(): String? {
    val encodedPath = this?.encodeURLPath()
    return IMAGE_BASE_URL + encodedPath
}
