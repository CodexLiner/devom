package com.devom.app.ui.screens.addbankaccount

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devom.Project
import com.devom.models.auth.UserRequestResponse
import com.devom.models.payment.UserBankDetails
import com.devom.utils.Application
import com.devom.utils.network.onResultNothing
import com.devom.utils.network.withSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BankAccountViewModel : ViewModel() {
    private val _bankAccount = MutableStateFlow(UserBankDetails())
    val bankAccount = _bankAccount.asStateFlow()

    private val _user = MutableStateFlow<UserRequestResponse?>(null)
    val user = _user
    init {
        getUserProfile()
    }


    fun updateBankAccount(details: UserBankDetails) {
        viewModelScope.launch {
            Project.payment.addBankDetailsUseCase.invoke(details.copy(userId = _user.value?.userId ?: 0)).collect {
                it.onResultNothing {
                    _bankAccount.value = details
                    Application.showToast("Bank account updated successfully")
                }
            }
        }
    }

    fun getUserProfile() {
        viewModelScope.launch {
            Project.user.getUserProfileUseCase.invoke().collect {
                it.withSuccess {
                    _user.value = it.data
                    getBankAccountDetails(it.data.userId)
                }
            }
        }
    }

    private fun getBankAccountDetails(userId: Int) {
        viewModelScope.launch {
            Project.payment.getBankDetailsUseCase.invoke(userId.toString()).collect {
                it.withSuccess {
                    _bankAccount.value = it.data
                }
            }
        }
    }
}