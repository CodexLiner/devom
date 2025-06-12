package com.devom.app.ui.screens.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.devom.app.theme.backgroundColor
import com.devom.app.theme.greyColor
import com.devom.app.theme.orangeShadow
import com.devom.app.theme.text_style_h4
import com.devom.app.theme.text_style_lead_body_1
import com.devom.app.theme.text_style_lead_text
import com.devom.app.theme.whiteColor
import com.devom.app.ui.components.ButtonPrimary
import com.devom.app.ui.components.ShapedScreen
import com.devom.app.ui.components.TextInputField
import com.devom.app.ui.navigation.Screens
import com.devom.app.ui.screens.signup.fragments.RegisterScreenMainContent
import com.devom.app.utils.isValid
import com.devom.app.utils.toColor
import com.devom.models.auth.CreateUserRequest
import com.devom.utils.Application
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import pandijtapp.composeapp.generated.resources.Res
import pandijtapp.composeapp.generated.resources.all_field_required
import pandijtapp.composeapp.generated.resources.ic_left
import pandijtapp.composeapp.generated.resources.referral_code

@Composable
fun RegisterMainScreen(
    navController: NavHostController,
    viewModel: SignUpViewModel = SignUpViewModel(),
) {
    val createUserStatus by viewModel.signUpState.collectAsStateWithLifecycle()
    var createUserRequest by remember {
        mutableStateOf(CreateUserRequest())
    }

    LaunchedEffect(createUserStatus) {
        if (createUserStatus == true) {
            navController.navigate(Screens.SignUpSuccess.path) {
                popUpTo(Screens.Register.path) {
                    inclusive = true
                }
            }
        }
    }

    ShapedScreen(
        headerContent = {
            RegisterScreenHeader(navController)
        },
        mainContent = {
            Column {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxSize().background(backgroundColor).weight(1f),
                    contentPadding = PaddingValues(horizontal = 24.dp, vertical = 40.dp)
                ) {

                    item {
                        RegisterScreenMainContent(createUserRequest) {
                            createUserRequest = it.copy()
                        }
                    }
                    item {
                        Text(
                            text = stringResource(Res.string.referral_code),
                            style = text_style_lead_text,
                            color = "#32343E".toColor()
                        )
                        TextInputField(
                            modifier = Modifier.padding(top = 4.dp),
                            placeholder = "Referral Code"
                        ) {
                            createUserRequest.referralCode = it
                        }
                    }
                }
                RegisterButtonContent(viewModel, createUserRequest, navController)
            }
        }
    )
}

@Composable
fun RegisterButtonContent(
    viewModel: SignUpViewModel,
    createUserRequest: CreateUserRequest,
    navController: NavHostController
) {
    val requiredText = stringResource(Res.string.all_field_required)
    Column(modifier = Modifier.navigationBarsPadding().padding(horizontal = 16.dp)) {
        ButtonPrimary(
            fontStyle = text_style_lead_text,
            modifier = Modifier.fillMaxWidth().height(58.dp),
            onClick = {
                val isValid = createUserRequest.isValid()
                if (isValid.first)
                    viewModel.signUp(createUserRequest)
                else Application.showToast(isValid.second ?: requiredText)
            }
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            val span = SpanStyle(
                color = orangeShadow,
                textDecoration = TextDecoration.Underline
            )
            val spannedText = buildAnnotatedString {
                append("I already have an account?")
                withStyle(span) {
                    append(" Login")
                }
            }

            Text(
                spannedText,
                color = greyColor,
                style = text_style_lead_body_1,
                modifier = Modifier.clickable {
                    navController.popBackStack(Screens.Login.path, false)
                }
            )
        }
    }
}

@Composable
fun RegisterScreenHeader(navController: NavHostController) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth()

    ) {
        Column(modifier = Modifier.fillMaxWidth().padding(bottom = 40.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.wrapContentSize()
                ) {
                    Image(
                        colorFilter = ColorFilter.tint(Color.White),
                        painter = painterResource(Res.drawable.ic_left),
                        contentDescription = "Back",
                        modifier = Modifier.size(24.dp)
                    )
                }
                Text(
                    text = "SignUp",
                    style = text_style_h4,
                    color = whiteColor,
                    modifier = Modifier.weight(1f)
                )
            }

//            Stepper(steps = steps, currentStep = currentStep.value)
        }
    }
}
