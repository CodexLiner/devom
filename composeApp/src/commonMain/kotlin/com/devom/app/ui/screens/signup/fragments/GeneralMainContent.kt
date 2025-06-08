package com.devom.app.ui.screens.signup.fragments

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.devom.app.ui.components.DatePickerDialog
import com.devom.models.auth.CreateUserRequest
import pandijtapp.composeapp.generated.resources.Res
import pandijtapp.composeapp.generated.resources.ic_phone
import com.devom.app.ui.components.TextInputField
import com.devom.app.ui.screens.profile.showDatePicker
import com.devom.utils.date.asDate
import com.devom.utils.date.convertIsoToDate
import com.devom.utils.date.convertToISOFormat
import com.devom.utils.date.toIsoDateTimeString
import com.devom.utils.date.toLocalDateTime
import org.jetbrains.compose.resources.painterResource
import pandijtapp.composeapp.generated.resources.calendar_linear

@Composable
internal fun GeneralMainContent(createUserRequest: CreateUserRequest , onChange : (CreateUserRequest) -> Unit) {
    val datePickerState = remember { mutableStateOf(false) }
    Column {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TextInputField(
                placeholder = "Enter name"
            ) {
                createUserRequest.fullName = it
                onChange(createUserRequest)
            }
            TextInputField(
                placeholder = "Enter email"
            ) {
                createUserRequest.email = it
                onChange(createUserRequest)
            }
            TextInputField(
                placeholder = "Enter phone"
            ) {
                createUserRequest.mobileNo = it
                onChange(createUserRequest)
            }
            TextInputField(
                placeholder = "Enter City"
            ) {
                createUserRequest.city = it
                onChange(createUserRequest)
            }

            TextInputField(
                placeholder = "Enter State"
            ) {
                createUserRequest.state = it
                onChange(createUserRequest)
            }
            TextInputField(
                placeholder = "Enter Country"
            ) {
                createUserRequest.country = it
                onChange(createUserRequest)
            }
            TextInputField(
                placeholder = "Address"
            ) {
                createUserRequest.address = it
                onChange(createUserRequest)
            }

            Box(
                modifier = Modifier.fillMaxWidth().clickable { datePickerState.value = true }) {
                val initialValue = createUserRequest.dateOfBirth.convertIsoToDate()?.toLocalDateTime()?.date

                TextInputField(
                    readOnly = true,
                    enabled = false,
                    initialValue = initialValue?.toString() ?: "",
                    placeholder = "Enter date of birth",
                    trailingIcon = {
                        Image(
                            painter = painterResource(Res.drawable.calendar_linear),
                            contentDescription = "Calendar",
                            modifier = Modifier.size(24.dp)
                        )
                    }) {
                    createUserRequest.dateOfBirth = it
                    onChange(createUserRequest)
                }
            }

            DatePickerDialog(
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
}