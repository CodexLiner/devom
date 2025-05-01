package org.company.app.ui.screens.signup.fragments

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import multiplatform_app.composeapp.generated.resources.Res
import multiplatform_app.composeapp.generated.resources.ic_phone
import org.company.app.ui.components.TextInputField
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun GeneralMainContent() {
    Column {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TextInputField(
                placeholder = "Enter name"
            )
            TextInputField(
                placeholder = "Enter email"
            )
            TextInputField(
                placeholder = "Enter phone"
            )
            TextInputField(
                placeholder = "Enter location"
            )
            TextInputField(
                placeholder = "Enter date of birth",
                trailingIcon = {
                    Image(
                        painter = painterResource(Res.drawable.ic_phone),
                        contentDescription = "Calendar",
                        modifier = Modifier.size(24.dp)
                    )
                }
            )

            Text(
                text = "Referral Code (optional)",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 8.dp)
            )

            TextInputField(
                placeholder = "Enter Code"
            )
        }
    }


}