package org.company.app.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devom.Project
import com.devom.utils.Application
import com.devom.utils.network.onResult
import com.devom.utils.network.withError
import com.devom.utils.network.withLoading
import com.devom.utils.network.withSuccess
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
                }
            }
        }
    }
}