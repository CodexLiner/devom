package com.devom.app.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.devom.app.models.SupportedFiles
import com.devom.app.theme.text_style_h3
import com.devom.app.theme.text_style_lead_text
import io.github.vinceglb.filekit.FileKit
import io.github.vinceglb.filekit.PlatformFile
import io.github.vinceglb.filekit.dialogs.FileKitType
import io.github.vinceglb.filekit.dialogs.openFilePicker
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilePickerBottomSheetHost(
    showSheet: Boolean,
    onDismissRequest: () -> Unit,
    allowedDocs: List<SupportedFiles> = listOf(
        SupportedFiles.AADHAAR_CARD,
        SupportedFiles.PAN_CARD,
        SupportedFiles.CERTIFICATE
    ),
    onFilePicked: (PlatformFile, SupportedFiles) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = onDismissRequest,
            sheetState = sheetState
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Pick a Document", style = text_style_h3)
                Spacer(modifier = Modifier.height(12.dp))

                allowedDocs.forEach { doc ->
                    ButtonPrimary(
                        fontStyle = text_style_lead_text,
                        buttonText = "Pick ${doc.document.replace("_", " ").lowercase().replaceFirstChar { it.uppercase() }}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        onClick = {
                            coroutineScope.launch {
                                val type = when {
                                    doc.mimeTypes.any { it.startsWith("image/") } -> FileKitType.Image
                                    doc.mimeTypes.any { it.startsWith("video/") } -> FileKitType.Video
                                    else -> FileKitType.File(doc.mimeTypes.toSet())
                                }

                                val file = FileKit.openFilePicker(type = type)
                                file?.let {
                                    onFilePicked(it, doc)
                                    onDismissRequest()
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}
