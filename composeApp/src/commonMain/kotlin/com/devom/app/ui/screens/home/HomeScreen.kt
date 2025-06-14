package com.devom.app.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.devom.app.BarChartExample
import com.devom.app.theme.backgroundColor
import com.devom.app.theme.blackColor
import com.devom.app.theme.text_style_h5
import com.devom.app.ui.components.AppBar
import com.devom.app.ui.components.NoContentView
import com.devom.app.ui.navigation.Screens
import com.devom.app.ui.screens.booking.components.BookingCard
import org.jetbrains.compose.resources.painterResource
import pandijtapp.composeapp.generated.resources.Res
import pandijtapp.composeapp.generated.resources.ic_notification

@Composable
fun HomeScreen(navHostController: NavHostController , onNavigationIconClick: () -> Unit) {
    val viewModel: HomeScreenViewModel = viewModel {
        HomeScreenViewModel()
    }
    LaunchedEffect(Unit) {
        viewModel.getBookings()
    }
    Column(modifier = Modifier.fillMaxSize().background(backgroundColor)) {
        AppBar(title = "Dashboard", onNavigationIconClick = onNavigationIconClick , actions = {
            IconButton(onClick = {
               navHostController.navigate(Screens.Notifications.path)
            }) {
                Icon(
                    painterResource(Res.drawable.ic_notification),
                    contentDescription = null,
                    tint = Color.White
                )
            }
        })
        HomeScreenContent(viewModel, navHostController)
    }
}

@Composable
fun HomeScreenContent(viewModel: HomeScreenViewModel, navHostController: NavHostController) {
    val bookings = viewModel.bookings.collectAsState()
    val transactions = viewModel.transactions.collectAsState()
    if (bookings.value.isEmpty()) NoContentView(
        message = "No Bookings Found",
        title = null,
        image = null
    ) else
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(
                start = 16.dp,
                end = 16.dp,
                top = 16.dp,
                bottom = 200.dp
            ),
            modifier = Modifier.fillMaxSize()
        ) {

            item {
                BarChartExample(transactions.value.transactions)
            }

            item {
                Text(
                    text = "Recent Bookings",
                    style = text_style_h5,
                    color = blackColor
                )
            }
            items(bookings.value.take(5)) { booking ->
                BookingCard(booking, onBookingUpdate = {
                    viewModel.updateBookingStatus(booking.bookingId, it)
                }, onClick = {
                    navHostController.navigate(Screens.BookingDetails.path + "/${booking.bookingId}")
                })
            }
        }
}