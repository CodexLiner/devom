package com.devom.app.ui.screens.reviews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devom.Project
import com.devom.models.auth.UserRequestResponse
import com.devom.models.pandit.Review
import com.devom.utils.network.onResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ReviewsAndRatingsViewModel : ViewModel() {
    private val _reviews = MutableStateFlow<List<Review>>(emptyList())
    val reviews = _reviews

    private val _user = MutableStateFlow<UserRequestResponse?>(null)
    val user = _user

    init {
        getUserProfile()
    }

    fun getUserProfile() {
        viewModelScope.launch {
            Project.user.getUserProfileUseCase.invoke().collect {
                it.onResult {
                    _user.value = it.data
                    getReviews(user.value?.userId.toString())
                }
            }
        }
    }

    fun getReviews(panditId : String) {
        viewModelScope.launch {
            Project.pandit.getPanditReviewsUseCase.invoke(panditId).collect {
                it.onResult {
                    _reviews.value = it.data.reviews
                }
            }
        }
    }
}