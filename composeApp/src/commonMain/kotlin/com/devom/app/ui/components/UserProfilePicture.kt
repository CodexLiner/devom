package com.devom.app.ui.components
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.devom.app.utils.toDevomImage
import com.devom.models.auth.UserRequestResponse
import org.jetbrains.compose.resources.painterResource
import pandijtapp.composeapp.generated.resources.Res
import pandijtapp.composeapp.generated.resources.ic_edit

@Composable
fun UserProfilePicture(
    modifier: Modifier = Modifier
        .size(100.dp)
        .clip(CircleShape)
        .border(2.dp, Color.White, CircleShape),
    userResponse: UserRequestResponse, onImageClick: () -> Unit = {},
) {
    Box(
        modifier = Modifier.fillMaxWidth().clickable(onClick = onImageClick),
        contentAlignment = Alignment.Center
    ) {
        Box(contentAlignment = Alignment.BottomEnd) {
            AsyncImage(
                model = userResponse.profilePictureUrl.toDevomImage(),
                modifier = modifier
            )

            Box(
                modifier = Modifier
                    .offset(x = (-4).dp, y = (-4).dp)
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(
                        Color(0xFFFFC107)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_edit),
                    contentDescription = "Edit",
                    modifier = Modifier.size(14.dp),
                    tint = Color.White
                )
            }
        }
    }
}
