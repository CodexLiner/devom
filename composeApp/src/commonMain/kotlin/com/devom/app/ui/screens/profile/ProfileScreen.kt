package com.devom.app.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.devom.app.theme.black_color
import com.devom.app.theme.primary_color
import com.devom.app.theme.text_style_h5
import com.devom.app.theme.text_style_lead_text
import com.devom.app.theme.white_color
import com.devom.app.ui.components.AppBar
import com.devom.app.ui.navigation.Screens
import com.devom.utils.Application
import org.jetbrains.compose.resources.painterResource
import pandijtapp.composeapp.generated.resources.Res
import pandijtapp.composeapp.generated.resources.arrow_drop_down_right
import pandijtapp.composeapp.generated.resources.ic_google
import pandijtapp.composeapp.generated.resources.ic_logout
import pandijtapp.composeapp.generated.resources.ic_menu
import pandijtapp.composeapp.generated.resources.placeholder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navHostController: NavHostController,
    rating: Float = 4.0f,
    progress: Int = 50,
    onNotificationToggle: (Boolean) -> Unit = {},
    onAvailabilityToggle: (Boolean) -> Unit = {}
) {
    val viewModel = viewModel<ProfileViewModel> {
        ProfileViewModel()
    }
    val user by viewModel.user.collectAsStateWithLifecycle()

    var notificationsEnabled by remember { mutableStateOf(false) }
    var availabilityEnabled by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        AppBar(
            title = "Profile",
            onNavigationIconClick = {
                navHostController.popBackStack()
            },
            actions = {
                IconButton(onClick = {
                    Application.logout()
                }) {
                    Icon(painterResource(Res.drawable.ic_logout), contentDescription = null, tint = Color.White)
                }
            }
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(16.dp)
                .clip(RoundedCornerShape(50))
                .background(Color(0xFFE0E0E0))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(progress / 100f)
                    .clip(RoundedCornerShape(50))
                    .background(Color(0xFF4CAF50)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "${progress}% Completed",
                    style = text_style_h5,
                    fontSize = 10.sp,
                    color = white_color,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(contentAlignment = Alignment.BottomEnd) {

                AsyncImage(
                    error = painterResource(Res.drawable.placeholder),
                    placeholder = painterResource(Res.drawable.ic_google),
                    model = user?.profilePictureUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.padding(top = 32.dp).size(115.dp).clip(CircleShape),
                )

                Box(
                    modifier = Modifier
                        .offset(x = (-4).dp, y = (-4).dp)
                        .size(24.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFFFC107)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = null,
                        modifier = Modifier.size(14.dp),
                        tint = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    user?.fullName.orEmpty(),
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Icon(Icons.Default.CheckCircle, contentDescription = null, tint = Color(0xFF4CAF50))
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                repeat(rating.toInt()) {
                    Icon(Icons.Default.Star, contentDescription = null, tint = Color(0xFF4CAF50))
                }
                repeat(5 - rating.toInt()) {
                    Icon(Icons.Default.Star, contentDescription = null, tint = Color(0xFFBDBDBD))
                }
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "(${rating}/5)", color = black_color , style = text_style_lead_text)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            colors = CardDefaults.cardColors().copy(containerColor = white_color),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {
            Column {
                ProfileOption("Update Profile") {
                    navHostController.navigate(Screens.EditProfile.path)
                }
                ProfileOption("Biography")
                ProfileOption("Documents")
                ProfileOption("Review & Ratings"){

                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Preferences Section
        Text(
            "Preferences",
            fontWeight = FontWeight.Bold,
            style = text_style_h5,
            color = black_color,
            modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
        )
        Card(
            colors = CardDefaults.cardColors().copy(containerColor = white_color),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {
            Column {
                PreferenceItem("Notifications", notificationsEnabled) {
                    notificationsEnabled = it
                    onNotificationToggle(it)
                }
                PreferenceItem("Availability", availabilityEnabled) {
                    availabilityEnabled = it
                    onAvailabilityToggle(it)
                }
            }
        }

        Spacer(modifier = Modifier.height(48.dp))
    }
}

@Composable
fun ProfileOption(title: String, onClick: () -> Unit = {}) {
    Row(
        modifier = Modifier.fillMaxWidth().clickable {
            onClick()
        }.padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(title)
        Icon(painterResource(Res.drawable.arrow_drop_down_right), "")
    }
    HorizontalDivider(thickness = 0.5.dp, color = Color.LightGray)
}

@Composable
fun PreferenceItem(title: String, isEnabled: Boolean, onToggle: (Boolean) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(title)
        Switch(
            colors = SwitchDefaults.colors().copy(checkedTrackColor = primary_color),
            checked = isEnabled,
            onCheckedChange = onToggle
        )
    }
    HorizontalDivider(thickness = 0.5.dp, color = Color.LightGray)
}