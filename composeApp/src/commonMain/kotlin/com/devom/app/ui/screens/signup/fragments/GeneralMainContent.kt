package com.devom.app.ui.screens.signup.fragments

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.devom.models.auth.CreateUserRequest
import pandijtapp.composeapp.generated.resources.Res
import pandijtapp.composeapp.generated.resources.ic_phone
import com.devom.app.ui.components.TextInputField
import com.devom.utils.date.asDate
import com.devom.utils.date.convertToISOFormat
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun GeneralMainContent(createUserRequest: CreateUserRequest , onChange : (CreateUserRequest) -> Unit) {
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

            TextInputField(
                placeholder = "Enter date of birth",
                trailingIcon = {
                    Image(
                        painter = painterResource(Res.drawable.ic_phone),
                        contentDescription = "Calendar",
                        modifier = Modifier.size(24.dp)
                    )
                }
            ) {
                createUserRequest.dateOfBirth = it
                onChange(createUserRequest)
            }

//            Text(
//                text = "Referral Code (optional)",
//                fontSize = 14.sp,
//                fontWeight = FontWeight.SemiBold,
//                modifier = Modifier.padding(top = 8.dp)
//            )
//
//            TextInputField(
//                placeholder = "Enter Code"
//            )
        }
    }
}