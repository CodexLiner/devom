package com.devom.app.ui.screens.notification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.devom.app.theme.greyColor
import com.devom.app.theme.primaryColor
import com.devom.app.theme.textBlackShade
import com.devom.app.ui.components.AppBar
import org.jetbrains.compose.resources.painterResource
import pandijtapp.composeapp.generated.resources.Res
import pandijtapp.composeapp.generated.resources.ic_arrow_left

@Composable
fun NotificationScreen(navHostController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize().background(Color.White)) {
        AppBar(
            navigationIcon = painterResource(Res.drawable.ic_arrow_left),
            title = "Notifications",
            onNavigationIconClick = { navHostController.popBackStack() })

        NotificationContent()


    }
}

@Composable
private fun NotificationContent() {
    LazyColumn(contentPadding = PaddingValues(16.dp)) {
        items(100) {
            NotificationItem()
        }
    }
}
@Composable
fun NotificationItem(
    imageUrl: String = "https://picsum.photos/200",
    title: String = "Profile Update",
    message: String = "You have a new booking for 'Ganesh Pooja' on 28th Mar 2025 at 10:00.",
    timestamp: String = "2 days ago",
    showDivider: Boolean = true
) {
    Column(
        modifier = Modifier
            .padding(vertical = 16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        NotificationContent(
            imageUrl = imageUrl,
            title = title,
            message = message,
            timestamp = timestamp
        )

        if (showDivider) {
            HorizontalDivider(color = greyColor.copy(alpha = 0.24f), thickness = 1.dp)
        }
    }
}

@Composable
private fun NotificationContent(
    imageUrl: String,
    title: String,
    message: String,
    timestamp: String
) {
    Row(
        modifier = Modifier.heightIn(max = 200.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        NotificationImage(imageUrl)
        NotificationTextContent(title, message, timestamp)
    }
}

@Composable
private fun NotificationImage(imageUrl: String) {
    AsyncImage(
        model = imageUrl,
        contentDescription = null,
        modifier = Modifier
            .size(44.dp)
            .background(color = primaryColor, shape = RoundedCornerShape(16.dp))
            .padding(10.dp)
    )
}

@Composable
private fun NotificationTextContent(
    title: String,
    message: String,
    timestamp: String
) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(
            buildAnnotatedString {
                withStyle(SpanStyle(fontWeight = FontWeight.W600, fontSize = 14.sp)) {
                    append(title)
                }
                append(": ")
                withStyle(SpanStyle(fontWeight = FontWeight.W500, fontSize = 14.sp)) {
                    append(message)
                }
            },
            color = textBlackShade,
            lineHeight = 18.sp
        )

        Text(
            text = timestamp,
            color = greyColor,
            fontSize = 12.sp,
            fontWeight = FontWeight.W600
        )
    }
}
