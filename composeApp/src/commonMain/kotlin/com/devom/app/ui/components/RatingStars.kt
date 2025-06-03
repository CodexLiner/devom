package com.devom.app.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.painterResource
import pandijtapp.composeapp.generated.resources.Res
import pandijtapp.composeapp.generated.resources.ic_star

@Composable
fun RatingStars(modifier: Modifier, rating: Int = 0, tint: Color = Color(0xFF4CAF50)) {
    Row(modifier, verticalAlignment = Alignment.CenterVertically) {
        repeat(rating) {
            Icon(
                painter = painterResource(Res.drawable.ic_star),
                contentDescription = null,
                tint = tint
            )
        }
    }
}