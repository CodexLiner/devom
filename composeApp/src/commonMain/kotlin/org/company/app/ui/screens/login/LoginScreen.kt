package org.company.app.ui.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import co.touchlab.kermit.Logger
import multiplatform_app.composeapp.generated.resources.Res
import multiplatform_app.composeapp.generated.resources.devom_logo
import multiplatform_app.composeapp.generated.resources.ic_cyclone
import multiplatform_app.composeapp.generated.resources.ic_google
import multiplatform_app.composeapp.generated.resources.ic_rotate_right
import multiplatform_app.composeapp.generated.resources.sign_in_with_google
import multiplatform_app.composeapp.generated.resources.text_dont_have_account
import multiplatform_app.composeapp.generated.resources.text_login
import multiplatform_app.composeapp.generated.resources.text_login_description
import multiplatform_app.composeapp.generated.resources.text_phone_number
import multiplatform_app.composeapp.generated.resources.text_sign_up
import org.company.app.EMPTY
import org.company.app.theme.black_color
import org.company.app.theme.grey_color
import org.company.app.theme.orange_shadow
import org.company.app.theme.text_style_h2
import org.company.app.theme.text_style_h5
import org.company.app.theme.text_style_lead_body_1
import org.company.app.theme.text_style_lead_text
import org.company.app.theme.white_color
import org.company.app.ui.components.ButtonPrimary
import org.company.app.ui.components.ShapedScreen
import org.company.app.ui.components.TextInputField
import org.company.app.utils.toColor
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun LoginScreen(navController: NavController) {
    ShapedScreen(headerContent = {
        LoginHeaderContent()
    }, mainContent = {
        LoginMainContent()
    })
}

@Composable
fun LoginMainContent() {
    Column {
        Column(
            modifier = Modifier.fillMaxSize().padding(top = 48.dp).padding(horizontal = 24.dp)
                .weight(1f),
            horizontalAlignment = Alignment.Start
        ) {
            /**
             * login text
             */
            Text(
                text = stringResource(Res.string.text_login),
                style = text_style_h2,
                color = black_color
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = stringResource(Res.string.text_login_description),
                style = text_style_h5,
                color = grey_color
            )

            /**
             * login text input area
             */
            Text(
                modifier = Modifier.padding(top = 52.dp, bottom = 3.dp),
                text = stringResource(Res.string.text_phone_number),
                style = text_style_lead_text,
                color = "#32343E".toColor()
            )
            TextInputField()
            ButtonPrimary(modifier = Modifier.padding(top = 16.dp).fillMaxWidth().height(58.dp)) {
                Logger.d("Yes clicked")
            }

            /**
             * divider
             */
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.padding(vertical = 32.dp)
            ) {
                Box(modifier = Modifier.fillMaxWidth().height(0.5.dp).background(grey_color))
                Box(
                    modifier = Modifier.wrapContentSize().background(white_color)
                        .padding(horizontal = 5.dp), contentAlignment = Alignment.Center
                ) {
                    Text(text = "OR", color = grey_color, style = text_style_lead_text)
                }
            }

            /**
             * login with google
             */
            ButtonPrimary(
                buttonText = stringResource(Res.string.sign_in_with_google),
                textColor = Color.Black,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                ),
                leadingIcon = {
                    Image(
                        painter = painterResource(Res.drawable.ic_google),
                        contentDescription = EMPTY,
                        modifier = Modifier.size(24.dp)
                    )
                },
                modifier = Modifier.padding(top = 16.dp).fillMaxWidth().height(58.dp)
                    .border(1.dp, color = grey_color.copy(0.2f), shape = RoundedCornerShape(12.dp))
            ) {

            }

        }

        Box(
            modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            val span = SpanStyle(color = orange_shadow, textDecoration = TextDecoration.Underline)
            val spannedText = buildAnnotatedString {
                append(stringResource(Res.string.text_dont_have_account))
                withStyle(span) {
                    append(stringResource(Res.string.text_sign_up))
                }
            }

            Text(spannedText, color = grey_color, style = text_style_lead_body_1)
        }
    }
}

@Composable
private fun LoginHeaderContent() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(Res.drawable.devom_logo),
            modifier = Modifier.size(188.dp),
            contentDescription = EMPTY
        )
    }
}