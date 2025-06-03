package com.devom.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devom.app.models.SupportedFiles
import com.devom.app.theme.inputColor
import com.devom.app.utils.dashedBorder
import io.github.vinceglb.filekit.PlatformFile
import org.jetbrains.compose.resources.painterResource
import pandijtapp.composeapp.generated.resources.Res
import pandijtapp.composeapp.generated.resources.ic_upload

@Composable
fun DocumentPicker(
    modifier: Modifier = Modifier.padding(horizontal = 16.dp),
    message: String = "Upload your file here",
    title: String,
    allowedDocs: List<SupportedFiles> = listOf(
        SupportedFiles.AADHAAR_CARD,
        SupportedFiles.PAN_CARD,
        SupportedFiles.CERTIFICATE,
        SupportedFiles.OTHER
    ),
    onFilePicked: (PlatformFile, SupportedFiles) -> Unit = { _, _ -> }
) {
    var showPicker by remember { mutableStateOf(false) }
    var selectedFileInfo by remember { mutableStateOf<Pair<PlatformFile, SupportedFiles>?>(null) }

    FilePickerBottomSheetHost(
        showSheet = showPicker,
        allowedDocs = allowedDocs,
        onDismissRequest = { showPicker = false },
        onFilePicked = { file, type ->
            selectedFileInfo = file to type
            onFilePicked(file, type) // ✅ invoke callback
        }
    )

    Column(verticalArrangement = Arrangement.spacedBy(8.dp), modifier = modifier) {
        Text(
            text = title,
            color = Color.Black,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .dashedBorder(
                    dashLength = 3.dp,
                    gapLength = 1.dp,
                    color = inputColor,
                    shape = RoundedCornerShape(8.dp)
                )
                .background(Color(0xFFF6F9FF), shape = RoundedCornerShape(8.dp))
                .clickable { showPicker = true },
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    painter = painterResource(Res.drawable.ic_upload),
                    contentDescription = null,
                    tint = Color.Gray.copy(alpha = 0.6f)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = message,
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }
        }
    }
}
