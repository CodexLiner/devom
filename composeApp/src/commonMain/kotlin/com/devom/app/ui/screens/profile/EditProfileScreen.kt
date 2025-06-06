package com.devom.app.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.devom.app.theme.text_style_lead_text
import com.devom.app.ui.components.AppBar
import com.devom.app.ui.components.AsyncImage
import com.devom.app.ui.components.ButtonPrimary
import com.devom.app.ui.components.DatePickerDialog
import com.devom.app.ui.components.TextInputField
import com.devom.app.utils.toDevomImage
import com.devom.models.auth.UserResponse
import com.devom.utils.date.convertIsoToDate
import com.devom.utils.date.toIsoDateTimeString
import com.devom.utils.date.toLocalDateTime
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
    Column(modifier = Modifier.fillMaxSize().background(Color.White)) {
        AppBar(
            navigationIcon = painterResource(Res.drawable.ic_arrow_left),
            title = "Edit Profile",
            onNavigationIconClick = { navHostController.popBackStack() }
        )
        user?.let {
            EditProfileScreenContent(viewModel, it)
        }
    }
}

@Composable
fun ColumnScope.EditProfileScreenContent(viewModel: ProfileViewModel, user: UserResponse) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = Modifier.weight(1f)
    ) {
        EditProfileFormContent(user, viewModel)
    }
    ButtonPrimary(
        modifier = Modifier.navigationBarsPadding().fillMaxWidth().padding(horizontal = 16.dp).height(58.dp),
        buttonText = "Update",
        onClick = { viewModel.updateUserProfile(user) },
        fontStyle = text_style_lead_text
    )
}

@Composable
fun EditProfileFormContent(userResponse: UserResponse, viewModel: ProfileViewModel) {
    val datePickerState = remember { mutableStateOf(false) }
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(start = 16.dp , end = 16.dp , top = 16.dp , bottom = 200.dp)
    ) {
        item {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = userResponse.profilePictureUrl.toDevomImage(),
                    modifier = Modifier.size(100.dp).clip(CircleShape).border(2.dp, Color.White, CircleShape)
                )
            }
        }

        item {
            TextInputField(
                initialValue = userResponse.fullName,
                placeholder = "Enter name"
            ) { userResponse.fullName = it }
        }

        item {
            TextInputField(
                initialValue = userResponse.email,
                placeholder = "Enter email"
            ) { userResponse.email = it }
        }

        item {
            TextInputField(
                initialValue = userResponse.mobileNo,
                placeholder = "Enter phone"
            ) { userResponse.mobileNo = it }
        }

        item {
            TextInputField(
                initialValue = userResponse.city.toString(),
                placeholder = "Enter City"
            ) { userResponse.city = it }
        }

        item {
            TextInputField(
                initialValue = userResponse.state.toString(),
                placeholder = "Enter State"
            ) { userResponse.state = it }
        }

        item {
            TextInputField(
                initialValue = userResponse.country.toString(),
                placeholder = "Enter Country"
            ) { userResponse.country = it }
        }

        item {
            TextInputField(
                initialValue = userResponse.country.toString(),
                placeholder = "Address"
            ) { userResponse.country = it }
        }

        item {
            TextInputField(
                initialValue = userResponse.address.toString(),
                placeholder = "Address"
            ) { userResponse.address = it }
        }

        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { datePickerState.value = true }
            ) {
                TextInputField(
                    readOnly = true,
                    enabled = false,
                    initialValue = userResponse.dateOfBirth.convertIsoToDate()
                        ?.toLocalDateTime()?.date.toString(),
                    placeholder = "Enter date of birth",
                    trailingIcon = {
                        Image(
                            painter = painterResource(Res.drawable.calendar_linear),
                            contentDescription = "Calendar",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                )
            }
        }
    }

    showDatePicker(datePickerState, viewModel, userResponse)
}


@Composable
fun showDatePicker(
    state: MutableState<Boolean>,
    viewModel: ProfileViewModel,
    userResponse: UserResponse,
) {
    DatePickerDialog(
        onDismiss = { state.value = false },
        onDateSelected = {
            state.value = false
            userResponse.dateOfBirth = it.toIsoDateTimeString()
            viewModel.setUserResponse(userResponse.copy())
        },
        showPicker = state.value,
        selectedDate = userResponse.dateOfBirth.convertIsoToDate()?.toLocalDateTime()?.date,
    )
}
