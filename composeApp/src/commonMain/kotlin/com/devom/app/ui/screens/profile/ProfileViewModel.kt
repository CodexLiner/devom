package com.devom.app.ui.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devom.Project
import com.devom.models.auth.UserResponse
import com.devom.utils.date.convertIsoToDate
import com.devom.utils.date.toLocalDateTime
import com.devom.utils.network.onResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    private val _user = MutableStateFlow<UserResponse>(UserResponse())
    val user = _user

    init {
        getUserProfile()
    }

    fun getUserProfile() {
        viewModelScope.launch {
            Project.user.getUserProfileUseCase.invoke().collect {
                it.onResult {
                    _user.value = it.data
                }
            }
        }
    }

    fun updateUserProfile(userResponse: UserResponse) {
        viewModelScope.launch {
            Project.user.updateUserProfileUseCase.invoke(
                userResponse.copy(
                    dateOfBirth = if (userResponse.dateOfBirth.contains("T")) userResponse.dateOfBirth.convertIsoToDate()
                        ?.toLocalDateTime()?.date.toString() else userResponse.dateOfBirth
                )
            ).collect {
                it.onResult {
                    _user.value = it.data
                }
            }
        }
    }

    fun setUserResponse(userResponse: UserResponse) {
        viewModelScope.launch {
            _user.emit(userResponse.copy())
        }
    }
}