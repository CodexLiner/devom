package com.devom.app.ui.screens.document

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.devom.app.ui.components.AppBar
import org.jetbrains.compose.resources.painterResource
import pandijtapp.composeapp.generated.resources.Res
import pandijtapp.composeapp.generated.resources.ic_arrow_left

@Composable
fun UploadDocumentScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize().background(Color.White)) {
        AppBar(
            navigationIcon = painterResource(Res.drawable.ic_arrow_left),
            title = "Notifications",
            onNavigationIconClick = { navController.popBackStack() })
        UploadDocumentScreenContent()
    }
}

@Composable
fun UploadDocumentScreenContent() {

}