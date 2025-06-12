package com.devom.app.ui.screens.transactions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devom.Project
import com.devom.models.auth.UserRequestResponse
import com.devom.models.payment.GetWalletTransactionsResponse
import com.devom.utils.network.onResult
import com.devom.utils.network.withSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class TransactionsScreenViewModel : ViewModel() {
    private val _transactions = MutableStateFlow(GetWalletTransactionsResponse())
    val transactions = _transactions

    private val _user = MutableStateFlow<UserRequestResponse?>(null)
    val user = _user

    init {
        getUserProfile()
    }

    fun getTransactions(userId : String) {
        viewModelScope.launch {
            Project.payment.getWalletTransactionsUseCase.invoke(userId).collect {
                it.onResult {
                    _transactions.value = it.data
                }
            }
        }
    }


    fun getUserProfile() {
        viewModelScope.launch {
            Project.user.getUserProfileUseCase.invoke().collect {
                it.withSuccess {
                    _user.value = it.data
                    getTransactions(_user.value?.userId.toString())
                }
            }
        }
    }
}