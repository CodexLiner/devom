package org.company.app.ui.screens.signup

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import org.company.app.ui.components.TextInputField
import org.company.app.ui.navigation.Screens
import org.jetbrains.compose.resources.painterResource

@Composable
fun GeneralScreen(navController: NavController) {
    val steps = listOf("General", "Skills", "Document")
    val currentStep = 2

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
                    .fillMaxSize() // 👈 Ensures full height usage
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


@Composable
private fun GeneralMainContent() {
    Column {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp).padding(top = 40.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TextInputField(
                placeholder = "Enter name"
            )
            TextInputField(
                placeholder = "Enter email"
            )
            TextInputField(
                placeholder = "Enter phone"
            )
            TextInputField(
                placeholder = "Enter location"
            )
            TextInputField(
                placeholder = "Enter date of birth",
                trailingIcon = {
                    Image(
                        painter = painterResource(Res.drawable.ic_phone),
                        contentDescription = "Calendar",
                        modifier = Modifier.size(24.dp)
                    )
                }
            )

            Text(
                text = "Referral Code (optional)",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 8.dp)
            )

            TextInputField(
                placeholder = "Enter Code"
            )
        }
    }


}


@Composable
fun Stepper(
    steps: List<String>,
    currentStep: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        steps.forEachIndexed { index, label ->
            StepItem(
                label = label,
                isCompleted = index < currentStep,
                isCurrent = index == currentStep
            )

            if (index < steps.lastIndex) {
                // Connector line
                Spacer(
                    modifier = Modifier
                        .weight(1f)
                        .height(1.dp)
                        .background(Color(0xFFE8751A)) // orange line
                )
            }
        }
    }
}

@Composable
fun StepItem(
    label: String,
    isCompleted: Boolean,
    isCurrent: Boolean
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .clip(CircleShape)
                .background(
                    when {
                        isCompleted -> Color(0xFFE8751A) // Orange
                        isCurrent -> Color.White
                        else -> Color.Gray
                    },
                    shape = CircleShape
                )
                .border(
                    width = 2.dp,
                    color = Color.White,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            when {
                isCompleted -> Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Completed",
                    tint = Color.White,
                    modifier = Modifier.size(14.dp)
                )

                isCurrent -> Canvas(modifier = Modifier.size(6.dp)) {
                    drawCircle(color = Color.Black)
                }
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall.copy(color = Color.White)
        )
    }
}
