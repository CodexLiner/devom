package com.devom.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.devom.app.ui.components.AppBar

@Composable
fun HomeScreen(modifier: Modifier = Modifier, navHostController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        AppBar(
            title = "Dashboard",
            onNavigationIconClick = {},
        )
    }
}