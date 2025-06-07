package com.devom.app.ui.screens.wallet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.devom.app.theme.backgroundColor
import com.devom.app.theme.bgColor
import com.devom.app.theme.blackColor
import com.devom.app.theme.greyColor
import com.devom.app.theme.primaryColor
import com.devom.app.theme.textBlackShade
import com.devom.app.theme.textStyleBody2
import com.devom.app.theme.text_style_h4
import com.devom.app.theme.text_style_lead_text
import com.devom.app.theme.whiteColor
import com.devom.app.ui.components.AppBar
import com.devom.app.utils.toColor
import org.jetbrains.compose.resources.painterResource
import pandijtapp.composeapp.generated.resources.Res
import pandijtapp.composeapp.generated.resources.arrow_drop_down_right
import pandijtapp.composeapp.generated.resources.ic_nav_wallet
import pandijtapp.composeapp.generated.resources.ic_transactions

@Composable
fun WalletScreen(navHostController: NavHostController, onNavigationIconClick: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().background(backgroundColor)) {
        AppBar(title = "My Wallet", onNavigationIconClick = onNavigationIconClick)
        WalletScreenContent(navHostController)
    }
}

@Composable
fun WalletScreenContent(navHostController: NavHostController) {
    WalletDetailsContent(navHostController)

}

@Composable
fun WalletDetailsContent(navController: NavHostController) {
    Box(modifier = Modifier.fillMaxWidth().background(primaryColor)) {
        WalletHeader()
    }
    WalletBreakdownRow()
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        WalletActionItem(
            painter = painterResource(Res.drawable.ic_transactions),
            text = "My Transactions",
            description = "View and track your payments and transactions."
        ) {

        }

        WalletActionItem(
            painter = painterResource(Res.drawable.ic_transactions),
            text = "Invite & Collect",
            description = "Bring your friends on DevOM and earn rewards"
        ) {

        }
    }
}

@Composable
private fun WalletHeader() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 12.dp)
            .background(bgColor, RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
            .border(
                width = 1.dp,
                color = whiteColor,
                shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
            )
            .padding(16.dp)
    ) {
        WalletIcon()
        WalletBalanceInfo()
        WithdrawButton()
    }
}

@Composable
private fun WalletIcon() {
    Image(
        painter = painterResource(Res.drawable.ic_nav_wallet),
        contentDescription = null,
        modifier = Modifier
            .background(whiteColor, RoundedCornerShape(12.dp))
            .padding(10.dp)
    )
}

@Composable
private fun RowScope.WalletBalanceInfo() {
    Column(modifier = Modifier.weight(1f)) {
        Text(
            text = "Current Balance",
            color = greyColor,
            fontWeight = FontWeight.W400,
            fontSize = 14.sp
        )

        Text(
            text = "₹1500",
            color = blackColor,
            style = text_style_h4
        )
    }
}

@Composable
private fun WithdrawButton() {
    TextButton(
        onClick = {},
        content = {
            Text(
                modifier = Modifier
                    .background(blackColor, RoundedCornerShape(12.dp))
                    .padding(vertical = 10.dp, horizontal = 16.dp),
                text = "Add Account",
                color = whiteColor,
                style = text_style_lead_text,
            )
        }
    )
}

@Composable
private fun WalletBreakdownRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(whiteColor, RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp))
            .border(
                width = 1.dp,
                color = "#6469823D".toColor().copy(.24f),
                shape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp)
            )
    ) {
        AvailableCashTypeItem(title = "Cash Amount", amount = "₹1450")
        AvailableCashTypeItem(title = "Cash Bonus ", amount = "₹50")
    }
}


@Composable
fun RowScope.AvailableCashTypeItem(title: String, amount: String = "") {
    Column(
        modifier = Modifier.weight(1f).padding(vertical = 18.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.W500,
            fontSize = 14.sp,
            color = greyColor
        )

        Text(
            text = amount,
            fontWeight = FontWeight.W600,
            fontSize = 16.sp,
            color = blackColor
        )
    }
}

@Composable
fun WalletActionItem(
    painter: Painter,
    text: String,
    description: String = "",
    onClick: () -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = Modifier.fillMaxWidth()
            .background(whiteColor, RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 16.dp),
    ) {
        Row(
            verticalAlignment = Alignment.Top
        ) {
            Image(painter = painter, contentDescription = null)
            Column(
                modifier = Modifier.padding(start = 16.dp).weight(1f),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(text = text, style = text_style_lead_text, color = textBlackShade)
                Text(text = description, style = textStyleBody2, color = greyColor)
            }
            Image(
                painter = painterResource(Res.drawable.arrow_drop_down_right),
                contentDescription = null,
            )
        }
    }
}