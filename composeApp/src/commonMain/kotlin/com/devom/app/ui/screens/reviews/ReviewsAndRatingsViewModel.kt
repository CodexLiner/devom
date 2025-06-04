package com.devom.app.ui.screens.reviews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devom.Project
import com.devom.models.pandit.GetReviewsResponse
import com.devom.models.pandit.Review
import com.devom.utils.network.onResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ReviewsAndRatingsViewModel : ViewModel() {
    private val _reviews = MutableStateFlow<List<Review>>(emptyList())
    val reviews = _reviews

    fun getReviews(panditId : String = "29") {
        viewModelScope.launch {
            Project.pandit.getPanditReviewsUseCase.invoke(panditId).collect {
                it.onResult {
                    _reviews.value = it.data.reviews
                }
            }
        }
    }
}