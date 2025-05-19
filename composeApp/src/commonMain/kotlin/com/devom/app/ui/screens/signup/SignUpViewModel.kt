package com.devom.app.ui.screens.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devom.Project
import com.devom.models.auth.CreateUserRequest
import com.devom.utils.network.onResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {
    private val _signUpState = MutableStateFlow<Boolean?>(null)
    val signUpState: StateFlow<Boolean?> = _signUpState

    fun signUp(user: CreateUserRequest) {
        viewModelScope.launch {
            Project.user.registerUserUseCase.invoke(user).collect {
                it.onResult {
                    _signUpState.value = true
                }
            }
        }
    }
}