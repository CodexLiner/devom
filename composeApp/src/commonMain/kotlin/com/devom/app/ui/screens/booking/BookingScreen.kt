package com.devom.app.ui.screens.booking

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.devom.app.theme.backgroundColor
import com.devom.app.theme.greenColor
import com.devom.app.theme.greyColor
import com.devom.app.theme.primaryColor
import com.devom.app.theme.text_style_h2
import com.devom.app.theme.text_style_lead_text
import com.devom.app.theme.whiteColor
import com.devom.app.ui.components.AppBar
import com.devom.models.slots.GetBookingsResponse
import org.jetbrains.compose.resources.painterResource
import pandijtapp.composeapp.generated.resources.Res
import pandijtapp.composeapp.generated.resources.ic_google
import pandijtapp.composeapp.generated.resources.placeholder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingScreen(navHostController: NavHostController) {
    val viewModel: BookingViewModel = viewModel { BookingViewModel() }
    val bookings = viewModel.bookings.collectAsState()
    val tabs = listOf("Pending", "Completed", "Cancelled")
    var selectedTabIndex = remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        viewModel.getBookings()
    }

    Column(
        modifier = Modifier.fillMaxSize().background(backgroundColor)
    ) {
        AppBar(title = "Bookings")
        BookingStatusTab(selectedTabIndex, tabs)
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(bookings.value) { booking ->
                BookingCard(booking) {

                }
            }
        }
    }
}

@Composable
fun BookingStatusTab(selectedTabIndex: MutableState<Int>, tabs: List<String>) {
    TabRow(
        modifier = Modifier.fillMaxWidth(),
        selectedTabIndex = selectedTabIndex.value,
        contentColor = Color.Black,
        indicator = { tabPositions ->
            SecondaryIndicator(
                Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex.value]).height(3.dp),
                color = Color(0xFFFF6F00)
            )
        },
        containerColor = Color.White
    ) {
        tabs.forEachIndexed { index, title ->
            Tab(
                selected = selectedTabIndex.value == index,
                onClick = { selectedTabIndex.value = index },
                text = {
                    Text(
                        title,
                        color = if (selectedTabIndex.value == index) Color(0xFFFF6F00) else Color.Gray,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.fillMaxWidth()
                    )
                })
        }
    }
}

@Composable
fun BookingCard(
    booking: GetBookingsResponse,
    statusColor: Color = greenColor,
    onClick : () -> Unit = {}
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth().background(whiteColor, shape = RoundedCornerShape(12.dp)).clickable { onClick() }
    ) {

        AsyncImage(
            error = painterResource(Res.drawable.placeholder),
            placeholder = painterResource(Res.drawable.ic_google),
            model = booking.userImage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(112.dp, 139.dp).clip(RoundedCornerShape(12.dp)),
        )
        Column(modifier = Modifier.weight(1f).padding(vertical = 12.dp)) {
            BookingUserDetail(booking, statusColor = statusColor)
            BookingUserContactDetail(booking = booking)
            HorizontalDivider(
                thickness = 1.dp, modifier = Modifier.padding(vertical = 12.dp), color = greyColor
            )
            BookingPoojaDetails(booking = booking)
        }
    }
}


@Composable
fun BookingUserDetail(booking: GetBookingsResponse, statusColor: Color) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            color = Color.Black,
            text = booking.userName,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.weight(1f)
        )

        Box(
            modifier = Modifier.background(Color(0x1AFFC107), shape = RoundedCornerShape(50))
                .padding(horizontal = 8.dp, vertical = 2.dp)
        ) {
            Text(
                booking.status,
                color = statusColor,
                fontWeight = FontWeight.W500,
                fontSize = 12.sp,
                lineHeight = 18.sp,
            )
        }
    }
}

@Composable
fun BookingUserContactDetail(booking: GetBookingsResponse) {
    Text(
        text = booking.mobileNo,
        fontWeight = FontWeight.W500,
        fontSize = 12.sp,
        lineHeight = 18.sp,
        color = greyColor,
    )
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            modifier = Modifier.weight(1f),
            text = booking.address.ifEmpty { "N/A" },
            fontWeight = FontWeight.W500,
            fontSize = 12.sp,
            lineHeight = 18.sp,
            color = greyColor,
        )
        Text(
            modifier = Modifier,
            text = "₹1101",
            fontSize = 14.sp,
            style = text_style_h2,
            color = primaryColor
        )

    }
}

@Composable
fun BookingPoojaDetails(booking: GetBookingsResponse) {
    Row {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                "POOJA TYPE",
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                style = text_style_lead_text,
                color = Color.Gray
            )
            Text(
                booking.poojaName.ifEmpty { "Pooja type not specified" },
                fontWeight = FontWeight.W500,
                fontSize = 12.sp,
                lineHeight = 18.sp,
                color = greyColor
            )
        }
        Column(modifier = Modifier.weight(1f)) {
            Text(
                "DATE & TIME",
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                style = text_style_lead_text,
                color = Color.Gray
            )
            Text(
                booking.bookingDate.toString(),
                fontWeight = FontWeight.W500,
                fontSize = 12.sp,
                lineHeight = 18.sp,
                color = greyColor
            )
        }
    }
}