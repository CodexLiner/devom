package org.company.app.ui.screens.register

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.devom.utils.Application
import multiplatform_app.composeapp.generated.resources.Res
import multiplatform_app.composeapp.generated.resources.resend_otp
import multiplatform_app.composeapp.generated.resources.resend_otp_message
import multiplatform_app.composeapp.generated.resources.verify_mobile_number
import multiplatform_app.composeapp.generated.resources.we_have_sent_the_verification_code
import org.company.app.theme.black_color
import org.company.app.theme.grey_color
import org.company.app.theme.orange_shadow
import org.company.app.theme.text_style_h2
import org.company.app.theme.text_style_lead_body_1
import org.company.app.theme.text_style_lead_text
import org.company.app.ui.components.BackButton
import org.company.app.ui.components.ButtonPrimary
import org.company.app.ui.components.OtpView
import org.company.app.ui.navigation.Screens
import org.jetbrains.compose.resources.stringResource

@Composable
fun VerifyOtpScreen(navController: NavController, mobileNumber: String?) {

    val viewModel = viewModel { RegisterViewModel() }
    var otpState: String by remember { mutableStateOf("") }

    BackButton(onClick = {
        navController.navigateUp()
    }) {
        Column(
            modifier = Modifier.fillMaxSize().statusBarsPadding().padding(top = 42.dp).padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.Start
        ) {
            /**
             * verify otp text
             */
            Text(
                text = stringResource(Res.string.verify_mobile_number),
                style = text_style_h2,
                textAlign = TextAlign.Start,
                color = black_color
            )

            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = stringResource(Res.string.we_have_sent_the_verification_code, mobileNumber.toString()),
                style = text_style_lead_text,
                textAlign = TextAlign.Start,
            )

            OtpView(modifier = Modifier.padding(top = 48.dp)) {
                otpState = it
            }

            ButtonPrimary(modifier = Modifier.padding(top = 48.dp).fillMaxWidth().height(58.dp)) {
                if (otpState.isNotBlank()) {
                    viewModel.verifyOtp(mobileNumber = mobileNumber.orEmpty(), otp = otpState) {
                        navController.navigate(Screens.SignUpSuccess.path)
                    }
                } else Application.showToast("Please enter otp")
            }

            Box(
                modifier = Modifier.fillMaxWidth().padding(vertical = 24.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                val span =
                    SpanStyle(color = orange_shadow, textDecoration = TextDecoration.Underline)
                val spannedText = buildAnnotatedString {
                    append(stringResource(Res.string.resend_otp_message))
                    withStyle(span) {
                        append(stringResource(Res.string.resend_otp))
                    }
                }

                Text(spannedText, color = grey_color, style = text_style_lead_body_1)
            }

        }
    }
}