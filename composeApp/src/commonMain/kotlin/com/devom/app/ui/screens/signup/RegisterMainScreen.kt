package com.devom.app.ui.screens.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clipScrollableContainer
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import com.devom.models.auth.CreateUserRequest
import pandijtapp.composeapp.generated.resources.Res
import pandijtapp.composeapp.generated.resources.ic_left
import com.devom.app.theme.greyColor
import com.devom.app.theme.orangeShadow
import com.devom.app.theme.text_style_h4
import com.devom.app.theme.text_style_lead_body_1
import com.devom.app.theme.text_style_lead_text
import com.devom.app.theme.whiteColor
import com.devom.app.ui.components.ButtonPrimary
import com.devom.app.ui.components.ShapedScreen
import com.devom.app.ui.navigation.Screens
import com.devom.app.ui.screens.signup.fragments.GeneralMainContent
import com.devom.app.ui.screens.signup.fragments.SkillsMainContent
import com.devom.app.ui.screens.signup.fragments.UploadDocumentMainContent
import com.devom.utils.date.DD_MMM_yyyy
import com.devom.utils.date.asDate
import com.devom.utils.date.convertToISOFormat
import com.devom.utils.date.convertToIso8601
import kotlinx.datetime.Instant
import org.jetbrains.compose.resources.painterResource

@Composable
fun RegisterMainScreen(navController: NavHostController , viewModel: SignUpViewModel = SignUpViewModel()) {
    val createUserStatus by viewModel.signUpState.collectAsStateWithLifecycle()

    val currentStep = remember { mutableIntStateOf(0) }
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
            MainScreenHeader(navController , currentStep = currentStep)
        },
        mainContent = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                        .clipScrollableContainer(orientation = Orientation.Vertical)
                        .padding(top = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    when(currentStep.intValue) {
                        0 -> GeneralMainContent(createUserRequest) {
                            createUserRequest = it.copy()
                        }
                        1 -> SkillsMainContent()
                        2 -> UploadDocumentMainContent()
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(top = 24.dp, bottom = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ButtonPrimary(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(58.dp),
                        onClick = {
                            viewModel.signUp(createUserRequest)
                        },
                        fontStyle = text_style_lead_text
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

                        Text(spannedText, color = greyColor, style = text_style_lead_body_1)
                    }
                }
            }
        }
    )
}

@Composable
fun MainScreenHeader(navController: NavHostController, currentStep: MutableIntState) {
    val steps = listOf("General", "Skills", "Document")

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth()

    ) {
        Column(modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp)) {
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
