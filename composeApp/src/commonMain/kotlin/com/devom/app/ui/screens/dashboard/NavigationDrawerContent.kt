package com.devom.app.ui.screens.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.devom.app.theme.primaryColor
import com.devom.app.theme.text_style_h5
import com.devom.app.theme.whiteColor
import com.devom.app.ui.components.AsyncImage
import com.devom.app.ui.components.UserProfilePicture
import com.devom.app.ui.navigation.Screens
import com.devom.app.utils.toColor
import com.devom.app.utils.toDevomImage
import com.devom.models.auth.UserRequestResponse
import com.devom.models.payment.GetWalletBalanceResponse
import kotlinx.datetime.Month
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import pandijtapp.composeapp.generated.resources.Biography
import pandijtapp.composeapp.generated.resources.Res
import pandijtapp.composeapp.generated.resources.Review_and_Ratings
import pandijtapp.composeapp.generated.resources.arrow_drop_down_right
import pandijtapp.composeapp.generated.resources.help_support
import pandijtapp.composeapp.generated.resources.ic_badge_verified
import pandijtapp.composeapp.generated.resources.ic_help_support
import pandijtapp.composeapp.generated.resources.ic_nav_bookings
import pandijtapp.composeapp.generated.resources.ic_nav_wallet
import pandijtapp.composeapp.generated.resources.ic_refer
import pandijtapp.composeapp.generated.resources.ic_review
import pandijtapp.composeapp.generated.resources.my_booking
import pandijtapp.composeapp.generated.resources.my_wallet
import pandijtapp.composeapp.generated.resources.refer_earn

@Composable
internal fun NavigationDrawerContent(
    user: UserRequestResponse?,
    appNavHostController: NavHostController,
    onWalletClick: () -> Unit,
    onBookings: () -> Unit,
    onDismiss: () -> Unit,
    balance: State<GetWalletBalanceResponse>,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .fillMaxHeight()
            .background(whiteColor),
    ) {
        UserDetailsContent(user, appNavHostController, balance, onDismiss)

        DrawerItem(
            painter = painterResource(Res.drawable.ic_nav_wallet),
            text = stringResource(Res.string.my_wallet)
        ) {
            onWalletClick()
        }

        DrawerItem(
            painter = painterResource(Res.drawable.ic_nav_bookings),
            text = stringResource(Res.string.my_booking)
        ) {
            onBookings()
        }

        DrawerItem(
            painter = painterResource(Res.drawable.ic_nav_wallet),
            text = stringResource(Res.string.Biography)
        ) {
            appNavHostController.navigate(Screens.Biography.path)
            onDismiss()
        }

        DrawerItem(
            painter = painterResource(Res.drawable.ic_review),
            text = stringResource(Res.string.Review_and_Ratings)
        ) {
            appNavHostController.navigate(Screens.ReviewsAndRatings.path)
            onDismiss()
        }

        DrawerItem(
            painter = painterResource(Res.drawable.ic_help_support),
            text = stringResource(Res.string.help_support)
        ) {
            appNavHostController.navigate(Screens.HelpAndSupport.path)
            onDismiss()
        }

        DrawerItem(
            painter = painterResource(Res.drawable.ic_refer),
            text = stringResource(Res.string.refer_earn)
        ) {
            appNavHostController.navigate(Screens.ReferAndEarn.path)
            onDismiss()
        }
    }
}


@Composable
fun UserDetailsContent(
    user: UserRequestResponse?,
    appNavHostController: NavHostController,
    balance: State<GetWalletBalanceResponse>,
    onDismiss: () -> Unit,
) {
    val currentBalance = (balance.value.balance.cashWallet.toIntOrNull()
        ?: 0) + (balance.value.balance.bonusWallet.toIntOrNull() ?: 0)

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().background(color = primaryColor).padding(16.dp)
            .statusBarsPadding(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        user?.let {
            UserProfilePicture(
                mainModifier = Modifier.wrapContentWidth(),
                modifier = Modifier.size(66.dp).clip(CircleShape),
                userResponse = user
            )
        }

        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(text = user?.fullName.orEmpty(), style = text_style_h5, color = whiteColor)
            val accountBalance = buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        fontWeight = FontWeight.W400,
                        fontSize = 14.sp,
                        color = whiteColor
                    )
                ) {
                    append("Account Balance:")
                }
                withStyle(
                    SpanStyle(
                        fontWeight = FontWeight.W600,
                        fontSize = 16.sp,
                        color = whiteColor
                    )
                ) {
                    append(" ₹$currentBalance")
                }
            }
            Text(text = accountBalance, style = text_style_h5, color = whiteColor)
            Text(
                modifier = Modifier.clickable {
                    appNavHostController.navigate(Screens.EditProfile.path)
                    onDismiss()
                },
                text = "Edit Profile",
                fontWeight = FontWeight.W500,
                fontSize = 12.sp,
                color = whiteColor
            )
        }
    }
}

@Composable
fun DrawerItem(painter: Painter, text: String, onClick: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = Modifier.fillMaxWidth().clickable { onClick() }.padding(horizontal = 16.dp)
            .padding(top = 18.dp),
    ) {
        Row(
            verticalAlignment = Alignment.Top
        ) {
            Image(painter = painter, contentDescription = null)
            Text(text = text, modifier = Modifier.padding(start = 16.dp).weight(1f))
            Image(
                painter = painterResource(Res.drawable.arrow_drop_down_right),
                contentDescription = null,
            )
        }
        HorizontalDivider(color = "#6469823D".toColor(), thickness = 1.dp)
    }
}