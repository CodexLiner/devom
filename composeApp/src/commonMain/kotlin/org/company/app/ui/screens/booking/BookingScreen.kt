package org.company.app.ui.screens.booking

import androidx.compose.foundation.Image
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import multiplatform_app.composeapp.generated.resources.Res
import multiplatform_app.composeapp.generated.resources.ic_google
import org.company.app.models.sampleBookings
import org.jetbrains.compose.resources.DrawableResource
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
                BookingCard(
                    name = booking.userName,
                    phone = booking.bookingCode,
                    address = booking.bookingCode,
                    poojaType = booking.poojaName,
                    dateTime = booking.startTime,
                    imageUrl = Res.drawable.ic_google
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
        }

    }
}

@Composable
fun BookingCard(
    name: String,
    phone: String,
    address: String,
    poojaType: String,
    dateTime: String,
    imageUrl: DrawableResource
) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .background(Color(0xFFFDFDFD), shape = RoundedCornerShape(12.dp)).padding(12.dp)
    ) {
        Image(
            painter = painterResource(imageUrl),
            contentDescription = null,
            modifier = Modifier.size(64.dp).clip(RoundedCornerShape(12.dp))
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.weight(1f)
                )

                Box(
                    modifier = Modifier.background(
                        Color(0x1AFFC107),
                        shape = RoundedCornerShape(50)
                    ).padding(horizontal = 8.dp, vertical = 2.dp)
                ) {
                    Text(
                        "Pending",
                        color = Color(0xFFFFC107),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            Text(text = phone, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            Text(text = address, style = MaterialTheme.typography.bodySmall, color = Color.Gray)

            Spacer(modifier = Modifier.height(8.dp))

            Row {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        "POOJA TYPE",
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp,
                        color = Color.Gray
                    )
                    Text(poojaType, style = MaterialTheme.typography.bodySmall)
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        "DATE & TIME",
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp,
                        color = Color.Gray
                    )
                    Text(dateTime, style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}
