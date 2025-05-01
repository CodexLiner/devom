package org.company.app.ui.screens.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import multiplatform_app.composeapp.generated.resources.Res
import multiplatform_app.composeapp.generated.resources.ic_phone
import org.company.app.theme.grey_color
import org.company.app.theme.orange_shadow
import org.company.app.theme.text_style_lead_body_1
import org.company.app.ui.components.ButtonPrimary
import org.company.app.ui.components.ShapedScreen
import org.company.app.ui.components.Stepper
import org.company.app.ui.components.TextInputField
import org.company.app.ui.navigation.Screens
import org.company.app.ui.screens.signup.fragments.GeneralMainContent
import org.jetbrains.compose.resources.painterResource

@Composable
fun GeneralScreen(navController: NavController) {
    val steps = listOf("General", "Skills", "Document")
    val currentStep = 0

    ShapedScreen(
        headerContent = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Stepper(steps = steps, currentStep = currentStep)
            }
        },
        mainContent = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 120.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    GeneralMainContent()
                }

                Column(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(horizontal = 24.dp, vertical = 16.dp)
                ) {
                    ButtonPrimary(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(58.dp)
                    ) {
                        navController.navigate(Screens.Document.path)
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        val span = SpanStyle(
                            color = orange_shadow,
                            textDecoration = TextDecoration.Underline
                        )
                        val spannedText = buildAnnotatedString {
                            append("I have already an account?")
                            withStyle(span) {
                                append(" Login")
                            }
                        }

                        Text(spannedText, color = grey_color, style = text_style_lead_body_1)
                    }
                }
            }
        }
    )
}