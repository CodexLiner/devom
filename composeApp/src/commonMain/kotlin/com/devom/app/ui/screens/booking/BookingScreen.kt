package com.devom.app.ui.screens.booking

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.devom.models.slots.GetBookingsResponse
import com.devom.utils.date.toIsoDate
import pandijtapp.composeapp.generated.resources.Res
import pandijtapp.composeapp.generated.resources.ic_google
import pandijtapp.composeapp.generated.resources.placeholder
import com.devom.app.theme.green_color
import com.devom.app.theme.grey_color
import com.devom.app.theme.primary_color
import com.devom.app.theme.text_style_h2
import com.devom.app.theme.text_style_lead_text
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingScreen(navHostController: NavHostController) {
    val viewModel : BookingViewModel = viewModel { BookingViewModel() }
    val bookings = viewModel.bookings.collectAsState()
    val tabs = listOf("Pending", "Completed", "Cancelled")
    var selectedTabIndex by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        viewModel.getBookings()
    }

    Column(
        modifier = Modifier.fillMaxSize().background(Color.White)
    ) {
        TopAppBar(
            title = { Text("Bookings") }, navigationIcon = {
                Icon(
                    Icons.Default.Menu,
                    contentDescription = null,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }, actions = {
                Text(
                    text = "Export",
                    color = Color.White,
                    modifier = Modifier.padding(end = 16.dp).clickable { /* Export action */ })
            }, colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFFFF6F00), titleContentColor = Color.White
            )
        )

        TabRow(
            modifier = Modifier.fillMaxWidth(),
            selectedTabIndex = selectedTabIndex,
            contentColor = Color.Black,
            indicator = { tabPositions ->
                SecondaryIndicator(
                    Modifier
                        .tabIndicatorOffset(tabPositions[selectedTabIndex])
                        .height(3.dp),
                    color = Color(0xFFFF6F00)
                )
            },
            containerColor = Color.White
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = {
                        Text(
                            title,
                            color = if (selectedTabIndex == index) Color(0xFFFF6F00) else Color.Gray,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.fillMaxWidth() // not required but safe
                        )
                    }
                )
            }
        }


        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(16.dp)
        ) {
            items(bookings.value) { booking ->
                BookingCard(booking)
                Spacer(modifier = Modifier.height(12.dp))
            }
        }

    }
}

@Composable
fun BookingCard(
    booking: GetBookingsResponse,
    statusColor: Color = green_color
) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .background(Color(0xFFFDFDFD), shape = RoundedCornerShape(12.dp)).padding(12.dp)
    ) {

        AsyncImage(
            error = painterResource(Res.drawable.placeholder),
            placeholder = painterResource(Res.drawable.ic_google),
            model = booking.userImage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(112.dp , 139.dp).clip(RoundedCornerShape(12.dp)),
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f).padding(vertical = 12.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    color = Color.Black,
                    text = booking.userName,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.weight(1f)
                )

                Box(modifier = Modifier.background(Color(0x1AFFC107), shape = RoundedCornerShape(50)).padding(horizontal = 8.dp, vertical = 2.dp)) {
                    Text(
                        booking.status,
                        color = statusColor,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            Text(text = booking.mobileNo, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            Row(verticalAlignment = Alignment.CenterVertically){
                Text(modifier = Modifier.weight(1f), text = booking.address.ifEmpty { "N/A" }, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                Text(modifier = Modifier, text = "₹1101", fontSize = 14.sp, style = text_style_h2, color = primary_color)

            }

            HorizontalDivider(thickness = 1.dp, modifier = Modifier.padding(vertical = 12.dp) , color = grey_color)

            Row {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        "POOJA TYPE",
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        style = text_style_lead_text,
                        color = Color.Gray
                    )
                    Text(booking.poojaName.ifEmpty { "Pooja type not specified" }, style = MaterialTheme.typography.bodySmall)
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        "DATE & TIME",
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        style = text_style_lead_text,
                        color = Color.Gray
                    )
                    Text(booking.bookingDate.toIsoDate().toString(), style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}
