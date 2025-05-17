package com.devom.app.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.devom.models.auth.UserResponse
import pandijtapp.composeapp.generated.resources.Res
import pandijtapp.composeapp.generated.resources.calendar_linear
import pandijtapp.composeapp.generated.resources.ic_cyclone
import com.devom.app.theme.grey_color
import com.devom.app.theme.text_style_lead_body_1
import com.devom.app.theme.text_style_lead_text
import com.devom.app.ui.components.ButtonPrimary
import com.devom.app.ui.components.TextInputField
import org.jetbrains.compose.resources.painterResource

@Composable
fun EditProfileScreen() {

    val viewModel = viewModel<ProfileViewModel> {
        ProfileViewModel()
    }

    val user by viewModel.user.collectAsState()

    user?.let {
        Column(modifier =  Modifier.padding(horizontal = 16.dp)) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(24.dp) , modifier = Modifier.weight(1f)) {
                Image(
                    painter = painterResource(Res.drawable.ic_cyclone),
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.White, CircleShape)
                )
                EditProfileFormContent(it)
            }

            Column(
                modifier = Modifier.fillMaxWidth().background(Color.White)
                    .padding(top = 24.dp, bottom = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ButtonPrimary(
                    modifier = Modifier.fillMaxWidth().height(58.dp),
                    buttonText = "Update",
                    onClick = {
                        user?.let {
                            viewModel.updateUserProfile(it)
                        }
                    },
                    fontStyle = text_style_lead_text
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Delete My Account", color = grey_color, style = text_style_lead_body_1)
                }
            }
        }
    }
}

@Composable
fun EditProfileFormContent(userResponse: UserResponse) {
    Column {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TextInputField(
                initialValue = userResponse.fullName,
                placeholder = "Enter name"
            ) {
                userResponse.fullName = it

            }
            TextInputField(
                initialValue = userResponse.email,
                placeholder = "Enter email"
            ) {
                userResponse.email = it

            }
            TextInputField(
                initialValue = userResponse.mobileNo,
                placeholder = "Enter phone"
            ) {
                userResponse.mobileNo = it

            }
            TextInputField(
                initialValue = userResponse.city.toString(),
                placeholder = "Enter City"
            ) {
                userResponse.city = it

            }

            TextInputField(
                initialValue = userResponse.state.toString(),
                placeholder = "Enter State"
            ) {
                userResponse.state = it

            }
            TextInputField(
                initialValue = userResponse.country.toString(),
                placeholder = "Enter Country"
            ) {
                userResponse.country = it

            }

            TextInputField(
                initialValue = userResponse.country.toString(),
                placeholder = "Address"
            ) {
                userResponse.country = it
            }

            TextInputField(
                initialValue = userResponse.dateOfBirth,
                placeholder = "Enter date of birth",
                trailingIcon = {
                    Image(
                        painter = painterResource(Res.drawable.calendar_linear),
                        contentDescription = "Calendar",
                        modifier = Modifier.size(24.dp)
                    )
                }
            ) {
                userResponse.dateOfBirth = it
            }
        }
    }
}