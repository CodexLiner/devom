package com.devom.app.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devom.Project
import com.devom.utils.Application.showToast
import com.devom.utils.network.onResult
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    /**
     * send otp on the entered mobile number
     */
    fun sendOtp(mobileNumber: String, onOtpSent: () -> Unit) {
        viewModelScope.launch {
            Project.user.generateOtpUseCase.invoke(mobileNumber).collect {
                it.onResult {
                    onOtpSent()
                    showToast("otp sent successfully ${it.data.otp}")
                }
            }
        }
    }
}