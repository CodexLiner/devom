package com.devom.app.ui.screens.referandearn

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.devom.app.ui.components.AppBar
import org.jetbrains.compose.resources.painterResource
import pandijtapp.composeapp.generated.resources.Res
import pandijtapp.composeapp.generated.resources.ic_arrow_left

@Composable
fun ReferAndEarnScreen(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize().background(Color.White)) {
        AppBar(
            navigationIcon = painterResource(Res.drawable.ic_arrow_left),
            title = "Refer & Earn",
            onNavigationIconClick = { navController.popBackStack() }
        )
        ReferAndEarnScreenContent()
    }

}

@Composable
fun ReferAndEarnScreenContent() {
    Text(
        text = "Refer and Earn Screen",
    )
}