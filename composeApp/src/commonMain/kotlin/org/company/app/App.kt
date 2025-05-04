package org.company.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import co.touchlab.kermit.Logger
import com.devom.Project
import com.devom.models.auth.CreateUserRequest
import com.devom.network.AUTHORIZATION_HEADER_PREFIX
import com.devom.network.NetworkClient
import io.ktor.client.utils.buildHeaders
import io.ktor.http.HttpHeaders
import org.company.app.theme.AppTheme
import org.company.app.ui.navigation.AuthNavHost
import org.company.app.ui.screens.DashboardScreen

@Composable
internal fun App() = AppTheme {

    var isLoggedIn by remember { mutableStateOf(false) } // Replace with actual login state from ViewModel
    LaunchedEffect(Unit) {

        NetworkClient.configure {
            baseUrl = "https://api.devom.co.in/"

            setTokens(
                access = "a009adbb10410e20296ed3dd0ba6aa7f2465d879d3788178f200a6ddcf4e13af",
                refresh = "a64fcd3ca04cf5cce0757556e12e62f041d03de86d949c7f0f357c6979ea68a3"
            )

            addHeaders  {
                append("uuid", "3c9346b6-7bea-4a47-8da7-dfbebd6477a1")
            }

        }

        val createUserRequest = CreateUserRequest(
            fullName = "John Doe",
            email = "john.doe1@example.com",
            mobileNo = "1234567890",
            city = 101,
            state = 21,
            country = 1,
            dateOfBirth = "1990-01-01",
            userTypeId = 2
        )

//        Project.pooja.getPoojaUseCase.invoke().collect {
//            Logger.d("ResponseFromApiIs $it")
//        }

//        Project.user.registerUserUseCase.invoke(createUserRequest).collect {
//            println("RESULTISCAMEAND $it")
//        }
    }

    if (isLoggedIn) {
        DashboardScreen()
    } else {
        AuthNavHost(onLoginSuccess = {
            isLoggedIn = false
        })
    }
}
