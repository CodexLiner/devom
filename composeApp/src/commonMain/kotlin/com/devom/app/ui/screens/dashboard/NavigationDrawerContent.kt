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
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.devom.app.ui.navigation.Screens
import com.devom.app.utils.toColor
import com.devom.app.utils.toDevomImage
import com.devom.models.auth.UserResponse
import org.jetbrains.compose.resources.painterResource
import pandijtapp.composeapp.generated.resources.Res
import pandijtapp.composeapp.generated.resources.arrow_drop_down_right
import pandijtapp.composeapp.generated.resources.ic_badge_verified
import pandijtapp.composeapp.generated.resources.ic_help_support
import pandijtapp.composeapp.generated.resources.ic_nav_bookings
import pandijtapp.composeapp.generated.resources.ic_nav_wallet
import pandijtapp.composeapp.generated.resources.ic_refer
import pandijtapp.composeapp.generated.resources.ic_review

@Composable
internal fun NavigationDrawerContent(
    user: UserResponse?,
    appNavHostController: NavHostController,
    onWalletClick: () -> Unit,
    onBookings: () -> Unit,
    onDismiss: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth(.8f).fillMaxHeight().background(whiteColor),
    ) {
        UserDetailsContent(user)
        DrawerItem(
            painter = painterResource(Res.drawable.ic_nav_wallet),
            text = "My Wallet"
        ) {
            onWalletClick()
        }
        DrawerItem(
            painter = painterResource(Res.drawable.ic_nav_bookings),
            text = "My Booking"
        ) {
            onBookings()
        }
        DrawerItem(
            painter = painterResource(Res.drawable.ic_nav_wallet),
            text = "Biography"
        ) {
            appNavHostController.navigate(Screens.Biography.path)
            onDismiss()
        }
        DrawerItem(
            painter = painterResource(Res.drawable.ic_review),
            text = "Review & Ratings"
        ) {
            appNavHostController.navigate(Screens.ReviewsAndRatings.path)
            onDismiss()

        }
        DrawerItem(
            painter = painterResource(Res.drawable.ic_help_support),
            text = "Help & Support"
        ) {
            appNavHostController.navigate(Screens.HelpAndSupport.path)
            onDismiss()

        }
        DrawerItem(
            painter = painterResource(Res.drawable.ic_refer),
            text = "Refer & Earn"
        ) {
            appNavHostController.navigate(Screens.ReferAndEarn.path)
            onDismiss()
        }
    }
}

@Composable
fun UserDetailsContent(user: UserResponse?) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().background(color = primaryColor).systemBarsPadding()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(contentAlignment = Alignment.BottomEnd) {
            AsyncImage(
                model = user?.profilePictureUrl.toDevomImage(),
                modifier = Modifier.size(66.dp).clip(CircleShape),
            )

            Box(
                modifier = Modifier
                    .offset(x = (-4).dp, y = (-4).dp)
                    .size(24.dp)
                    .clip(CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(Res.drawable.ic_badge_verified),
                    contentDescription = null,
                    modifier = Modifier.size(14.dp),
                )
            }
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
                    append(" ₹ 2200")
                }
            }
            Text(text = accountBalance, style = text_style_h5, color = whiteColor)
            Text(
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
        modifier = Modifier.fillMaxWidth().clickable { onClick() }.padding(horizontal = 16.dp , vertical = 18.dp),
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