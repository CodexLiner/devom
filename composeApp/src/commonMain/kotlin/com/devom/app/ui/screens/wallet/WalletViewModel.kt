package com.devom.app.ui.screens.wallet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devom.Project
import com.devom.models.auth.UserRequestResponse
import com.devom.models.payment.GetWalletBalanceResponse
import com.devom.models.payment.UserBankDetails
import com.devom.utils.network.onResult
import com.devom.utils.network.withSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WalletViewModel : ViewModel() {
    private val _user = MutableStateFlow<UserRequestResponse?>(null)
    val user = _user

    private val _bankDetails = MutableStateFlow<UserBankDetails?>(null)
    val bankDetails = _bankDetails.asStateFlow()

    private val _walletBalances = MutableStateFlow(GetWalletBalanceResponse())
    val walletBalances = _walletBalances

    init {
        getUserProfile()
    }

    fun getUserProfile() {
        viewModelScope.launch {
            Project.user.getUserProfileUseCase.invoke().collect {
                it.withSuccess {
                    _user.value = it.data
                    getWalletBalance(user.value?.userId.toString())
                    getBankDetails(user.value?.userId.toString())
                }
            }
        }
    }

    fun getWalletBalance(userId: String) {
        viewModelScope.launch {
            Project.payment.getWalletBalanceUseCase.invoke(userId).collect {
                it.onResult {
                    _walletBalances.value = it.data
                }
            }
        }
    }

    fun getBankDetails(userId: String) {
        viewModelScope.launch {
            Project.payment.getBankDetailsUseCase.invoke(userId).collect {
                it.onResult {
                    _bankDetails.value = it.data
                }
            }
        }
    }
}