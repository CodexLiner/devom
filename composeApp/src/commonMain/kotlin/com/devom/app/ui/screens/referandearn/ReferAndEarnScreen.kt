package com.devom.app.ui.screens.referandearn

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.devom.app.theme.blackColor
import com.devom.app.theme.greyColor
import com.devom.app.theme.primaryColor
import com.devom.app.theme.text_style_h3
import com.devom.app.theme.whiteColor
import com.devom.app.ui.components.AppBar
import com.devom.app.ui.components.ShapedScreen
import com.devom.models.auth.UserResponse
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import pandijtapp.composeapp.generated.resources.Res
import pandijtapp.composeapp.generated.resources.copy
import pandijtapp.composeapp.generated.resources.ic_arrow_left
import pandijtapp.composeapp.generated.resources.ic_search
import pandijtapp.composeapp.generated.resources.img_social_friends
import pandijtapp.composeapp.generated.resources.invite_friends
import pandijtapp.composeapp.generated.resources.share

@Composable
fun ReferAndEarnScreen(navController: NavHostController) {
    val viewModel: ReferAndEarnViewModel = viewModel {
        ReferAndEarnViewModel()
    }
    val user = viewModel.user.collectAsState()
    Column(modifier = Modifier.fillMaxSize().background(Color.White)) {
        AppBar(
            navigationIcon = painterResource(Res.drawable.ic_arrow_left),
            title = "Refer & Earn",
            onNavigationIconClick = { navController.popBackStack() })
        ReferAndEarnScreenContent(user)
    }

}

@Composable
fun ReferAndEarnScreenContent(user: State<UserResponse?>) {
    ShapedScreen(
        modifier = Modifier.fillMaxSize().background(primaryColor),
        headerContent = {
            ReferHeaderContent(user)
        }, mainContent = {
            ReferMainContent()
        }
    )
}

@Composable
fun ReferMainContent() {
    Column(modifier = Modifier.fillMaxWidth().padding(24.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = stringResource(Res.string.invite_friends),
                style = text_style_h3,
                color = blackColor
            )
            Image(
                painter = painterResource(Res.drawable.ic_search),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }

    }
}

@Composable
fun ReferHeaderContent(user: State<UserResponse?>) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(Res.drawable.img_social_friends),
            contentDescription = null
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .weight(0.7f)
                    .background(whiteColor, RoundedCornerShape(16.dp))
                    .padding(horizontal = 14.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = user.value?.referralCode.orEmpty(),
                    color = greyColor
                )
                Text(
                    text = stringResource(Res.string.copy),
                    color = greyColor
                )
            }

            Text(
                text = stringResource(Res.string.share),
                color = whiteColor,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                modifier = Modifier
                    .weight(0.3f)
                    .background(blackColor, RoundedCornerShape(16.dp))
                    .padding(vertical = 14.dp, horizontal = 16.dp)
            )
        }
    }
}
