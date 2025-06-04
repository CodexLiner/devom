package com.devom.app.ui.screens.signup.fragments
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.devom.app.ui.components.DocumentPicker

@Composable
fun UploadDocumentMainContent(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .padding(bottom = 120.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        DocumentPicker(title = "Aadhaar Card")
        DocumentPicker(title = "PAN Card")
        DocumentPicker(title = "Certificates")
    }
}


