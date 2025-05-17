package org.company.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.company.app.models.BottomNavItem
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun BottomNavigationBar(
    selectedIndex: Int = 1,
    onItemSelected: (Int) -> Unit = {}
) {
    val items = listOf(
        BottomNavItem("Home", Icons.Default.Home, selectedIndex == 0),
        BottomNavItem("Booking", Icons.Default.MoreVert, selectedIndex == 1),
        BottomNavItem("Wallet", Icons.Default.AccountBox, selectedIndex == 2),
        BottomNavItem("Profile", Icons.Default.Person, selectedIndex == 3)
    )

    Box(
        Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.take(2).forEachIndexed { index, item ->
                BottomNavItem(item, index, onItemSelected)
            }

            Spacer(modifier = Modifier.width(56.dp))

            items.drop(2).forEachIndexed { index, item ->
                BottomNavItem(item, index + 2, onItemSelected)
            }
        }

        Box(
            modifier = Modifier
                .size(64.dp)
                .align(Alignment.TopCenter)
                .offset(y = (-32).dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFFFFA726), Color(0xFFFFC107))
                    ),
                    shape = CircleShape
                )
                .clickable { /* FAB Action */ },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add",
                tint = Color.White,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}


@Composable
fun BottomNavItem(
    item: BottomNavItem,
    index: Int,
    onClick: (Int) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable(interactionSource = null, indication = null, onClick = {
                onClick(index)
            })
            .padding(horizontal = 8.dp)
    ) {
        Icon(
            imageVector = item.icon,
            contentDescription = item.label,
            tint = if (item.isSelected) Color(0xFFFF6F00) else Color.Gray,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = item.label,
            color = if (item.isSelected) Color(0xFFFF6F00) else Color.Gray,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        )
        if (item.isSelected) {
            Box(
                modifier = Modifier
                    .height(2.dp)
                    .width(24.dp)
                    .background(Color(0xFFFF6F00), shape = RoundedCornerShape(1.dp))
                    .padding(top = 2.dp)
            )
        }
    }
}
