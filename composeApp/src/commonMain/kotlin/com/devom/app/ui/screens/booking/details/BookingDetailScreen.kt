package com.devom.app.ui.screens.booking.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.devom.app.theme.backgroundColor
import com.devom.app.theme.greyColor
import com.devom.app.theme.textBlackShade
import com.devom.app.theme.text_style_h5
import com.devom.app.theme.text_style_lead_body_1
import com.devom.app.theme.text_style_lead_text
import com.devom.app.theme.whiteColor
import com.devom.app.ui.components.AppBar
import com.devom.app.ui.components.ButtonPrimary
import com.devom.app.ui.screens.booking.BookingCard
import com.devom.app.ui.screens.booking.BookingViewModel
import com.devom.app.utils.toColor
import com.devom.models.slots.GetBookingsResponse
import org.jetbrains.compose.resources.painterResource
import pandijtapp.composeapp.generated.resources.Res
import pandijtapp.composeapp.generated.resources.ic_arrow_left

@Composable
fun BookingDetailScreen(navController: NavController, booking: GetBookingsResponse) {
    val viewModel: BookingViewModel = viewModel { BookingViewModel() }
    Column(
        modifier = Modifier.fillMaxSize().background(backgroundColor)
    ) {
        AppBar(
            title = "Booking Details",
            navigationIcon = painterResource(Res.drawable.ic_arrow_left),
            onNavigationIconClick = { navController.popBackStack() }
        )
        BookingDetailScreenContent(navController, booking , viewModel)
        ButtonPrimary(
            modifier = Modifier.fillMaxWidth().navigationBarsPadding().padding(horizontal = 16.dp, vertical = 16.dp).height(58.dp),
            buttonText = "Start Pooja"
        )
    }
}

@Composable
fun ColumnScope.BookingDetailScreenContent(
    navController: NavController,
    booking: GetBookingsResponse,
    viewModel: BookingViewModel,
) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
    ) {
        item {
            BookingCard(
                booking = booking,
                onBookingUpdate = { viewModel.updateBookingStatus(booking.bookingId, it) }
            )
        }

        item {
            Text(
                text = "Pooja Samagri List",
                style = text_style_h5,
                color = textBlackShade,
                modifier = Modifier.padding(top = 28.dp , bottom = 16.dp)
            )
        }

        itemsIndexed(List(10) { it }) { index, _ ->
            val shape = when (index) {
                0 -> RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
                9 -> RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp)
                else -> RoundedCornerShape(0.dp)
            }
            SamagriItemRow(
                item = booking,
                modifier = Modifier.background(color = whiteColor, shape = shape).padding(horizontal = 16.dp)
            ) {
                // Handle checkbox state change here
            }

            if (index < 9) HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp), color = "#A0A5BA3D".toColor(), thickness = 1.dp)
        }
    }
}


@Composable
fun SamagriItemRow(modifier: Modifier = Modifier, item: GetBookingsResponse, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = modifier.fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = true,
            onCheckedChange = onCheckedChange
        )
        Text(
            style = text_style_lead_body_1,
            text = "item.name",
            color = textBlackShade,
            modifier = Modifier.weight(1f)
        )
        Text(text = "10", fontSize = 12.sp, color = greyColor, style = text_style_lead_text)
    }
}

