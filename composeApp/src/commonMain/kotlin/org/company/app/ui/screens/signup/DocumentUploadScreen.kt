package org.company.app.ui.screens.signup


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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import org.company.app.ui.components.ButtonPrimary
import org.company.app.ui.components.ShapedScreen
import org.company.app.ui.components.Stepper
import org.company.app.ui.navigation.Screens
import org.company.app.ui.screens.signup.fragments.UploadSection


@Composable
fun DocumentUploadScreen(navHostController: NavHostController) {
    val steps = listOf("General", "Skills", "Document")
    val currentStep = 2
    ShapedScreen(
        headerContent = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Stepper(steps = steps, currentStep = currentStep)
            }
        },
        mainContent = {
            Box(modifier = Modifier.fillMaxSize().padding(top = 40.dp)) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 24.dp)
                        .padding(bottom = 120.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    UploadSection(title = "Aadhaar Card")
                    UploadSection(title = "PAN Card")
                    UploadSection(title = "Certificates")
                }

                // Bottom Submit Button
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(horizontal = 24.dp, vertical = 16.dp)
                ) {
                    ButtonPrimary(
                        buttonText = "Submit",
                        modifier = Modifier.padding(top = 48.dp).fillMaxWidth().height(58.dp)
                    ) {
                        navHostController.navigate(Screens.SignUpSuccess.path)
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text("I have already an account?", color = Color.Gray)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            "Login",
                            color = Color(0xFFFF6A00),
                            textDecoration = TextDecoration.Underline
                        )
                    }
                }
            }
        }
    )
}