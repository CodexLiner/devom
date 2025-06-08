package com.devom.app.ui.screens.wallet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devom.Project
import com.devom.models.auth.UserResponse
import com.devom.models.payment.GetWalletBalanceResponse
import com.devom.utils.network.onResult
import com.devom.utils.network.withSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class WalletViewModel : ViewModel() {
    private val _user = MutableStateFlow<UserResponse?>(null)
    val user = _user

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
}