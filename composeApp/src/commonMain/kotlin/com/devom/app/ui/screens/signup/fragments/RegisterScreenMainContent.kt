package com.devom.app.ui.screens.signup.fragments

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.devom.app.ui.components.DatePickerDialog
import com.devom.models.auth.CreateUserRequest
import pandijtapp.composeapp.generated.resources.Res
import com.devom.app.ui.components.TextInputField
import com.devom.utils.date.convertIsoToDate
import com.devom.utils.date.toIsoDateTimeString
import com.devom.utils.date.toLocalDateTime
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import pandijtapp.composeapp.generated.resources.calendar_icon_description
import pandijtapp.composeapp.generated.resources.calendar_linear
import pandijtapp.composeapp.generated.resources.date_of_birth
import pandijtapp.composeapp.generated.resources.enter_address
import pandijtapp.composeapp.generated.resources.enter_city
import pandijtapp.composeapp.generated.resources.enter_country
import pandijtapp.composeapp.generated.resources.enter_email
import pandijtapp.composeapp.generated.resources.enter_full_name
import pandijtapp.composeapp.generated.resources.enter_phone
import pandijtapp.composeapp.generated.resources.enter_state

@Composable
internal fun RegisterScreenMainContent(
    createUserRequest: CreateUserRequest,
    onChange: (CreateUserRequest) -> Unit,
) {
    val datePickerState = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TextInputField(
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            placeholder = stringResource(Res.string.enter_full_name)
        ) {
            createUserRequest.fullName = it
            onChange(createUserRequest)
        }
        TextInputField(
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            placeholder = stringResource(Res.string.enter_email)
        ) {
            createUserRequest.email = it
            onChange(createUserRequest)
        }
        TextInputField(
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            maxLength = 10,
            placeholder = stringResource(Res.string.enter_phone)
        ) {
            createUserRequest.mobileNo = it
            onChange(createUserRequest)
        }
        TextInputField(
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            placeholder = stringResource(Res.string.enter_city)
        ) {
            createUserRequest.city = it
            onChange(createUserRequest)
        }
        TextInputField(
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            placeholder = stringResource(Res.string.enter_state)
        ) {
            createUserRequest.state = it
            onChange(createUserRequest)
        }
        TextInputField(
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            placeholder = stringResource(Res.string.enter_country)
        ) {
            createUserRequest.country = it
            onChange(createUserRequest)
        }
        TextInputField(
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            placeholder = stringResource(Res.string.enter_address)
        ) {
            createUserRequest.address = it
            onChange(createUserRequest)
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { datePickerState.value = true }
        ) {
            val initialValue = createUserRequest.dateOfBirth
                .convertIsoToDate()
                ?.toLocalDateTime()
                ?.date

            TextInputField(
                readOnly = true,
                enabled = false,
                initialValue = initialValue?.toString() ?: "",
                placeholder = stringResource(Res.string.date_of_birth),
                trailingIcon = {
                    Image(
                        painter = painterResource(Res.drawable.calendar_linear),
                        contentDescription = stringResource(Res.string.calendar_icon_description),
                        modifier = Modifier.size(24.dp)
                    )
                }
            ) {
                createUserRequest.dateOfBirth = it
                onChange(createUserRequest)
            }
        }

        DatePickerDialog(
            maxDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date,
            onDismiss = { datePickerState.value = false },
            onDateSelected = {
                datePickerState.value = false
                val updatedUser = createUserRequest.copy(dateOfBirth = it.toIsoDateTimeString())
                onChange(updatedUser)
            },
            showPicker = datePickerState.value,
        )
    }
}
