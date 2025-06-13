package com.devom.app.ui.screens.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devom.Project
import com.devom.models.auth.UserRequestResponse
import com.devom.models.payment.GetWalletBalanceResponse
import com.devom.utils.network.withSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DashboardViewModel : ViewModel() {

    private val _user = MutableStateFlow<UserRequestResponse?>(null)
    val user = _user

    private val _walletBalances = MutableStateFlow(GetWalletBalanceResponse())
    val walletBalances = _walletBalances

    private var _selectedTab = MutableStateFlow(0)
    var selectedTab = _selectedTab
    fun onTabSelected(index: Int) {
        _selectedTab.value = index
    }
    init {
        getUserProfile()
    }

    fun getWalletBalance(userId: String) {
        viewModelScope.launch {
            Project.payment.getWalletBalanceUseCase.invoke(userId).collect {
                it.withSuccess {
                    _walletBalances.value = it.data
                }
            }
        }
    }

    fun getUserProfile() {
        viewModelScope.launch {
            Project.user.getUserProfileUseCase.invoke().collect {
                it.withSuccess {
                    _user.value = it.data
                    getWalletBalance(_user.value?.userId.toString())
                }
            }
        }
    }
}