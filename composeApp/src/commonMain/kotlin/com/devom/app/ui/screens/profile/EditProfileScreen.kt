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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import co.touchlab.kermit.Logger
import com.devom.app.models.SupportedFiles
import com.devom.app.theme.text_style_lead_text
import com.devom.app.ui.components.AppBar
import com.devom.app.ui.components.AsyncImage
import com.devom.app.ui.components.ButtonPrimary
import com.devom.app.ui.components.DatePickerDialog
import com.devom.app.ui.components.DocumentPicker
import com.devom.app.ui.components.FilePickerBottomSheetHost
import com.devom.app.ui.components.TextInputField
import com.devom.app.utils.isValid
import com.devom.app.utils.toDevomImage
import com.devom.models.auth.UserResponse
import com.devom.utils.Application
import com.devom.utils.date.convertIsoToDate
import com.devom.utils.date.toIsoDateTimeString
import com.devom.utils.date.toLocalDateTime
import io.github.vinceglb.filekit.name
import io.github.vinceglb.filekit.source
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.io.buffered
import kotlinx.io.readByteArray
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import pandijtapp.composeapp.generated.resources.Res
import pandijtapp.composeapp.generated.resources.all_field_required
import pandijtapp.composeapp.generated.resources.calendar_linear
import pandijtapp.composeapp.generated.resources.ic_arrow_left
import pandijtapp.composeapp.generated.resources.ic_edit

@Composable
fun EditProfileScreen(navHostController: NavHostController) {

    val viewModel = viewModel<ProfileViewModel> {
        ProfileViewModel()
    }
    val user by viewModel.user.collectAsState()
    Column(modifier = Modifier.fillMaxSize().background(Color.White)) {
        AppBar(
            navigationIcon = painterResource(Res.drawable.ic_arrow_left),
            title = "Edit Profile",
            onNavigationIconClick = { navHostController.popBackStack() }
        )

        EditProfileScreenContent(viewModel, user)

        LaunchedEffect(user) {
            Logger.d("DATE_OF_ BIRTH $user")
        }
    }
}

@Composable
fun ColumnScope.EditProfileScreenContent(viewModel: ProfileViewModel, user: UserResponse) {
    val focus = LocalFocusManager.current
    val requiredText = stringResource(Res.string.all_field_required)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = Modifier.weight(1f)
    ) {
        EditProfileFormContent(user, viewModel)
    }
    ButtonPrimary(
        fontStyle = text_style_lead_text,
        modifier = Modifier.navigationBarsPadding().fillMaxWidth().padding(horizontal = 16.dp).height(58.dp),
        buttonText = "Update",
        onClick = {
            focus.clearFocus()
            val isValid = user.isValid()
            if (isValid.first) viewModel.updateUserProfile(user)
            else Application.showToast(isValid.second ?: requiredText)
        },
    )
}

@Composable
fun EditProfileFormContent(userResponse: UserResponse, viewModel: ProfileViewModel) {
    val datePickerState = remember { mutableStateOf(false) }
    val imagePickerState = remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 200.dp)
    ) {
        item {
            Box(
                modifier = Modifier.fillMaxWidth().clickable {
                    imagePickerState.value = true
                },
                contentAlignment = Alignment.Center
            ) {
                Box(contentAlignment = Alignment.BottomEnd) {
                    AsyncImage(
                        model = userResponse.profilePictureUrl.toDevomImage(),
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .border(2.dp, Color.White, CircleShape)
                    )

                    Box(
                        modifier = Modifier
                            .offset(x = (-4).dp, y = (-4).dp)
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(
                                Color(0xFFFFC107)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_edit),
                            contentDescription = "Edit",
                            modifier = Modifier.size(14.dp),
                            tint = Color.White
                        )
                    }
                }
            }
        }

        item {
            TextInputField(
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                ),
                initialValue = userResponse.fullName,
                placeholder = "Enter name *"
            ) { userResponse.fullName = it }
        }

        item {
            TextInputField(
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                ),
                initialValue = userResponse.email,
                placeholder = "Enter email *"
            ) { userResponse.email = it }
        }

        item {
            TextInputField(
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Number
                ),
                initialValue = userResponse.mobileNo,
                placeholder = "Enter phone *"
            ) { userResponse.mobileNo = it }
        }

        item {
            TextInputField(
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                ),
                initialValue = userResponse.city.toString(),
                placeholder = "Enter City *"
            ) { userResponse.city = it }
        }

        item {
            TextInputField(
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                ),
                initialValue = userResponse.state.toString(),
                placeholder = "Enter State *"
            ) { userResponse.state = it }
        }

        item {
            TextInputField(
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                ),
                initialValue = userResponse.country.toString(),
                placeholder = "Enter Country *"
            ) { userResponse.country = it }
        }

        item {
            TextInputField(
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                initialValue = userResponse.address.toString(),
                placeholder = "Address *"
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
                    initialValue = if (userResponse.dateOfBirth.contains("T")) userResponse.dateOfBirth.convertIsoToDate()
                        ?.toLocalDateTime()?.date.toString() else userResponse.dateOfBirth,
                    placeholder = "Date of birth *",
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

    FilePickerBottomSheetHost(
        showSheet = imagePickerState.value,
        allowedDocs = listOf(SupportedFiles.IMAGE),
        onDismissRequest = {
            imagePickerState.value = false
        },
        onFilePicked = { file, type ->
            val image = file.source().buffered().readByteArray()
            userResponse.imageFileName = file.name
            viewModel.updateUserProfile(userResponse , image)
            imagePickerState.value = false
        }
    )
}


@Composable
fun showDatePicker(
    state: MutableState<Boolean>,
    viewModel: ProfileViewModel,
    userResponse: UserResponse,
) {

    DatePickerDialog(
        maxDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date,
        onDismiss = { state.value = false },
        onDateSelected = {
            state.value = false
            val updatedUser = userResponse.copy(dateOfBirth = it.toIsoDateTimeString())
            viewModel.setUserResponse(updatedUser)
        },
        showPicker = state.value,
        selectedDate = userResponse.dateOfBirth.convertIsoToDate()?.toLocalDateTime()?.date,
    )
}
