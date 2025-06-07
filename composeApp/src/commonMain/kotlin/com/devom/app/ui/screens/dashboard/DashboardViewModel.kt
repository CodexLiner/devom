package com.devom.app.ui.screens.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devom.Project
import com.devom.models.auth.UserResponse
import com.devom.user.User
import com.devom.utils.network.withSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DashboardViewModel : ViewModel() {

    private val _user = MutableStateFlow<UserResponse?>(null)
    val user = _user

    private var _selectedTab = MutableStateFlow(0)
    var selectedTab = _selectedTab
    fun onTabSelected(index: Int) {
        _selectedTab.value = index
    }
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