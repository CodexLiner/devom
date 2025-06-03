package com.devom.app.ui.screens.reviews

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.devom.app.ui.components.AppBar
import com.devom.app.ui.components.RatingStars
import com.devom.models.pandit.GetReviewsResponse
import org.jetbrains.compose.resources.painterResource
import pandijtapp.composeapp.generated.resources.Res
import pandijtapp.composeapp.generated.resources.ic_arrow_left
import pandijtapp.composeapp.generated.resources.placeholder
import pandijtapp.composeapp.generated.resources.vertical_ellipsis

@Composable
fun ReviewsAndRatingsScreen(navController: NavController) {
    val viewModel = viewModel { ReviewsAndRatingsViewModel() }
    LaunchedEffect(Unit) {
        viewModel.getReviews()
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
fun ReviewListContent(list: State<List<GetReviewsResponse>>) {
    LazyColumn(contentPadding = PaddingValues(16.dp)) {
        items(list.value.size) { index ->
            ReviewItem(review = list.value[index])
        }
    }
}

@Composable
fun ReviewItem(review: GetReviewsResponse) {
    Column {
        ReviewerDetailRow(review)
    }
}

@Composable
fun ReviewerDetailRow(review: GetReviewsResponse) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            modifier = Modifier.weight(1f),
            painter = painterResource(Res.drawable.placeholder),
            contentDescription = "Reviewer Image",
        )
        Column(modifier = Modifier.weight(2f) , verticalArrangement = Arrangement.SpaceEvenly) {
            Text(
                text = review.userId,
            )
            RatingStars(rating = review.rating.toInt() , modifier = Modifier.weight(1f))
        }
        Image(
            modifier = Modifier.weight(1f),
            painter = painterResource(Res.drawable.vertical_ellipsis),
            contentDescription = "Rating",
        )
    }
}

