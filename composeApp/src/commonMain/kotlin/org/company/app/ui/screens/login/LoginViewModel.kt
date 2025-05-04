package org.company.app.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devom.Project
import com.devom.utils.network.withSuccess
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    fun sendOtp(mobileNumber: String , onOtpSent : () -> Unit) {
        viewModelScope.launch {
            Project.user.generateOtpUseCase.invoke(mobileNumber).collect {
                it.withSuccess {
                    onOtpSent()
                }
            }
        }
    }
}