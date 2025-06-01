package com.devom.app.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.devom.app.theme.text_style_lead_text
import com.devom.app.ui.components.AppBar
import com.devom.app.ui.components.ButtonPrimary
import com.devom.app.ui.components.TextInputField
import com.devom.models.auth.UserResponse
import org.jetbrains.compose.resources.painterResource
import pandijtapp.composeapp.generated.resources.Res
import pandijtapp.composeapp.generated.resources.calendar_linear
import pandijtapp.composeapp.generated.resources.ic_arrow_left
import pandijtapp.composeapp.generated.resources.placeholder

@Composable
fun EditProfileScreen(navHostController: NavHostController) {

    val viewModel = viewModel<ProfileViewModel> {
        ProfileViewModel()
    }
    val user by viewModel.user.collectAsStateWithLifecycle()

    user?.let {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)

        ) {
            AppBar(
                navigationIcon = painterResource(Res.drawable.ic_arrow_left),
                title = "Edit Profile",
                onNavigationIconClick = { navHostController.popBackStack() })
            Column(modifier = Modifier.padding(horizontal = 16.dp).padding(top = 16.dp)) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    AsyncImage(
                        model = it.profilePictureUrl,
                        placeholder = painterResource(Res.drawable.placeholder),
                        error = painterResource(Res.drawable.placeholder),
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
                .fillMaxWidth().verticalScroll(rememberScrollState()),
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
                initialValue = userResponse.address.toString(),
                placeholder = "Address"
            ) {
                userResponse.address = it
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