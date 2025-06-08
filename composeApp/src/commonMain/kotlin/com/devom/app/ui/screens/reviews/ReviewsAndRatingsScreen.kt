package com.devom.app.ui.screens.reviews

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.devom.app.theme.greyColor
import com.devom.app.theme.text_style_lead_body_1
import com.devom.app.ui.components.AppBar
import com.devom.app.ui.components.AsyncImage
import com.devom.app.ui.components.NoContentView
import com.devom.app.ui.components.RatingStars
import com.devom.app.utils.toDevomImage
import com.devom.models.pandit.GetReviewsResponse
import com.devom.models.pandit.Review
import org.jetbrains.compose.resources.painterResource
import pandijtapp.composeapp.generated.resources.Res
import pandijtapp.composeapp.generated.resources.ic_arrow_left
import pandijtapp.composeapp.generated.resources.ic_no_reviews
import pandijtapp.composeapp.generated.resources.placeholder
import pandijtapp.composeapp.generated.resources.vertical_ellipsis

@Composable
fun ReviewsAndRatingsScreen(navController: NavController) {
    val viewModel = viewModel { ReviewsAndRatingsViewModel() }
    LaunchedEffect(Unit) {
        viewModel.getUserProfile()
    }
    Column(modifier = Modifier.fillMaxSize().background(Color.White)) {
        AppBar(
            navigationIcon = painterResource(Res.drawable.ic_arrow_left),
            title = "Review & Ratings",
            onNavigationIconClick = { navController.popBackStack() }
        )
        ReviewsAndRatingsContent(viewModel)
    }
}

@Composable
fun ReviewsAndRatingsContent(viewModel: ReviewsAndRatingsViewModel) {
    val reviews = viewModel.reviews.collectAsState()
    ReviewListContent(list = reviews)
}

@Composable
fun ReviewListContent(list: State<List<Review>>) {
    if (list.value.isEmpty()) {
        NoContentView(
            image = Res.drawable.ic_no_reviews,
            title = "No Reviews yet",
            message = "No reviews yet for this booking. Be the first to share your experience!"
        )
        return
    }
    LazyColumn(contentPadding = PaddingValues(start = 16.dp , end = 16.dp , top = 16.dp , bottom = 200.dp)) {
        items(list.value.size) { index ->
            ReviewItem(review = list.value[index])
        }
    }
}

@Composable
fun ReviewItem(review: Review) {
    Column(modifier = Modifier.padding(top = 24.dp)) {
        ReviewerDetailRow(review)
        Text(
            style = text_style_lead_body_1,
            text = review.reviewText,
            color = greyColor,
            modifier = Modifier.padding(top = 12.dp , end = 24.dp)
        )
        HorizontalDivider(modifier = Modifier.padding(top = 24.dp), color = greyColor.copy(alpha = 0.24f), thickness = 1.dp)

    }
}

@Composable
fun ReviewerDetailRow(review: Review) {
    Row(
        verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()
    ) {
        AsyncImage(
            modifier = Modifier.size(38.dp).clip(CircleShape),
            model = review.userImage.toDevomImage(),
        )
        Column(
            modifier = Modifier.weight(1f).padding(horizontal = 12.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = review.userName.ifEmpty { "user_${review.userId}" }.toString())
            RatingStars(modifier = Modifier.padding(top = 4.dp), rating = review.rating.toFloat())
        }

        Image(
            painter = painterResource(Res.drawable.vertical_ellipsis),
            contentDescription = "Options",
        )
    }
}

