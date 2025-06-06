package com.devom.app.ui.screens.booking.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.devom.models.slots.GetBookingsResponse
import com.devom.app.theme.greyColor
import com.devom.app.theme.textBlackShade
import com.devom.app.theme.text_style_lead_body_1
import com.devom.app.theme.text_style_lead_text

@Composable
fun BookingDetailScreen(navController: NavController ) {

}

@Composable
fun SamagriItemRow(item: GetBookingsResponse, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCheckedChange(true) }
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
