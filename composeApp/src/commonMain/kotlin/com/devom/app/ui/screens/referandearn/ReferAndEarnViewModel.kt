package com.devom.app.ui.screens.referandearn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devom.Project
import com.devom.models.auth.UserResponse
import com.devom.utils.network.withSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ReferAndEarnViewModel : ViewModel() {
    private val _user = MutableStateFlow<UserResponse?>(null)
    val user = _user

    init {
        getUserProfile()
    }

    fun getUserProfile() {
        viewModelScope.launch {
            Project.user.getUserProfileUseCase.invoke().collect {
                it.withSuccess {
                    _user.value = it.data
                }
            }
        }
    }

}