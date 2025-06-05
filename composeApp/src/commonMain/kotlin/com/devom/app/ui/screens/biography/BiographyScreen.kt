package com.devom.app.ui.screens.biography

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.devom.app.ui.components.AppBar
import com.devom.app.ui.components.DocumentPicker
import com.devom.app.ui.screens.document.DocumentItem
import org.jetbrains.compose.resources.painterResource
import pandijtapp.composeapp.generated.resources.Res
import pandijtapp.composeapp.generated.resources.ic_arrow_left

@Composable
fun BiographyScreen(navController: NavController) {
    val viewModel = viewModel {
        BiographyViewModel()
    }
    Column(modifier = Modifier.fillMaxSize().background(Color.White)) {
        AppBar(
            navigationIcon = painterResource(Res.drawable.ic_arrow_left),
            title = "Documents",
            onNavigationIconClick = { navController.popBackStack() })
        BiographyScreenScreenContent(viewModel)
    }
}

@Composable
fun BiographyScreenScreenContent(viewModel: BiographyViewModel) {


    Column (verticalArrangement = Arrangement.spacedBy(16.dp)){
        LazyColumn(contentPadding = PaddingValues(16.dp)) {
//            items(documents.value) {
//                DocumentItem(document = it)
//            }
        }
        DocumentPicker(title = "Media Gallery") { platformFile, supportedFiles ->
            viewModel.uploadDocument("29", platformFile, supportedFiles)
        }
    }
}