package com.devom.app.ui.screens.booking

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.devom.app.theme.backgroundColor
import com.devom.app.ui.components.AppBar
import com.devom.app.ui.components.NoContentView
import com.devom.app.ui.navigation.Screens
import com.devom.network.NetworkClient

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingScreen(navHostController: NavHostController) {
    val viewModel: BookingViewModel = viewModel { BookingViewModel() }
    val bookings = viewModel.bookings.collectAsState()
    val tabs = listOf("Pending", "Completed", "Rejected")
    var selectedTabIndex = remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        viewModel.getBookings()
    }

    Column(
        modifier = Modifier.fillMaxSize().background(backgroundColor)
    ) {
        AppBar(title = "Bookings")
        BookingStatusTab(selectedTabIndex, tabs)

        val filteredBookings =
            bookings.value.filter { it.status == tabs[selectedTabIndex.value].lowercase() }
        if (filteredBookings.isNotEmpty()) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(filteredBookings) { booking ->
                    BookingCard(booking, onBookingUpdate = {
                        viewModel.updateBookingStatus(booking.bookingId, it)
                    }, onClick = {
                        navHostController.navigate(
                            Screens.BookingDetails.path + "/${
                                NetworkClient.config.jsonConfig.encodeToString(
                                    booking
                                )
                            }"
                        )
                    })
                }
            }
        } else NoContentView(message = "No Bookings Found", title = null, image = null)
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