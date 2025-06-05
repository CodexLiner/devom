package com.devom.app.ui.screens.document

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.devom.app.models.SupportedFiles
import com.devom.app.theme.greenColor
import com.devom.app.theme.inputColor
import com.devom.app.theme.text_style_lead_text
import com.devom.app.theme.warningColor
import com.devom.app.theme.whiteColor
import com.devom.app.ui.components.AppBar
import com.devom.app.ui.components.DocumentPicker
import com.devom.models.document.GetDocumentResponse
import org.jetbrains.compose.resources.painterResource
import pandijtapp.composeapp.generated.resources.Res
import pandijtapp.composeapp.generated.resources.ic_arrow_left
import pandijtapp.composeapp.generated.resources.ic_upload

@Composable
fun UploadDocumentScreen(navController: NavController) {
    val viewModel = viewModel {
        UploadDocumentViewModel()
    }
    Column(modifier = Modifier.fillMaxSize().background(Color.White)) {
        AppBar(
            navigationIcon = painterResource(Res.drawable.ic_arrow_left),
            title = "Documents",
            onNavigationIconClick = { navController.popBackStack() })
        UploadDocumentScreenContent(viewModel)
    }
}

@Composable
private fun UploadDocumentScreenContent(viewModel: UploadDocumentViewModel) {
    val documents = viewModel.documents.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getDocuments()
    }

    Column (verticalArrangement = Arrangement.spacedBy(16.dp)){
        LazyColumn(contentPadding = PaddingValues(16.dp)) {
            items(documents.value) {
                DocumentItem(document = it)
            }
        }
        DocumentPicker(title = "Certificates") { platformFile, supportedFiles ->
            viewModel.uploadDocument("29", platformFile, supportedFiles)
        }
    }
}

@Composable
fun DocumentItem(
    modifier: Modifier = Modifier,
    document: GetDocumentResponse
) {
    Box(
        modifier = modifier
            .padding(vertical = 8.dp)
            .background(Color(0xFFF5F9FF), shape = RoundedCornerShape(16.dp))
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            DocumentIcon()

            DocumentTexts(
                documentName = getDocumentName(document.documentType),
                documentId = document.documentId
            )
        }

        VerificationChip(
            status = document.verificationStatus,
            modifier = Modifier.align(Alignment.TopEnd)
        )
    }
}


@Composable
private fun DocumentIcon() {
    Icon(
        painter = painterResource(Res.drawable.ic_upload),
        contentDescription = null,
        tint = Color.Gray,
        modifier = Modifier
            .size(32.dp)
            .padding(end = 12.dp)
    )
}

@Composable
private fun DocumentTexts(documentName: String, documentId: String) {
    Column {
        Text(
            text = documentName,
            style = text_style_lead_text,
            color = inputColor
        )
        Text(
            modifier = Modifier.padding(top = 4.dp),
            text = documentId,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

@Composable
private fun VerificationChip(status: String, modifier: Modifier = Modifier) {
    val chipColor = when (status.lowercase()) {
        "verified" -> greenColor
        "pending" -> warningColor
        else -> warningColor
    }

    Box(
        modifier = modifier
            .background(
                color = chipColor,
                shape = RoundedCornerShape(bottomStart = 12.dp, topEnd = 12.dp)
            )
    ) {
        Text(
            text = status,
            color = whiteColor,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
            fontWeight = FontWeight.W600,
            fontSize = 12.sp
        )
    }
}

private fun getDocumentName(type: String): String {
    return when (type) {
        SupportedFiles.AADHAAR_CARD.type -> SupportedFiles.AADHAAR_CARD.document
        SupportedFiles.PAN_CARD.type -> SupportedFiles.PAN_CARD.document
        SupportedFiles.CERTIFICATE.type -> SupportedFiles.CERTIFICATE.document
        else -> SupportedFiles.OTHER.document
    }
}

