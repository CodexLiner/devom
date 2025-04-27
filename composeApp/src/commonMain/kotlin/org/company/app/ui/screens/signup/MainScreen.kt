package org.company.app.ui.screens.signup

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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import multiplatform_app.composeapp.generated.resources.Res
import multiplatform_app.composeapp.generated.resources.ic_left
import org.company.app.theme.grey_color
import org.company.app.theme.orange_shadow
import org.company.app.theme.text_style_h4
import org.company.app.theme.text_style_lead_body_1
import org.company.app.theme.white_color
import org.company.app.ui.components.ButtonPrimary
import org.company.app.ui.components.ShapedScreen
import org.company.app.ui.components.Stepper
import org.company.app.ui.navigation.Screens
import org.jetbrains.compose.resources.painterResource

@Composable
fun MainScreen(navController: NavHostController) {

    ShapedScreen(
        headerContent = {
            MainScreenHeader(navController)
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
                    GeneralMainContent()
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
                            .height(58.dp)
                    ) {
                        navController.navigate(Screens.Document.path)
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        val span = SpanStyle(
                            color = orange_shadow,
                            textDecoration = TextDecoration.Underline
                        )
                        val spannedText = buildAnnotatedString {
                            append("I already have an account?")
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

@Composable
fun MainScreenHeader(navController: NavHostController) {
    val steps = listOf("General", "Skills", "Document")
    val currentStep = remember { mutableIntStateOf(0) }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    onClick = { navController.popBackStack() }, // Added back click too
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
                    color = white_color,
                    modifier = Modifier.weight(1f)
                )
            }

            Stepper(steps = steps, currentStep = currentStep.value)
        }
    }
}
