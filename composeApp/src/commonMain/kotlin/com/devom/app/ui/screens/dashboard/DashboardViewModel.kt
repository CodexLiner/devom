package com.devom.app.ui.screens.dashboard

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class DashboardViewModel : ViewModel() {
    private var _selectedTab = MutableStateFlow(1)
    var selectedTab = _selectedTab
    fun onTabSelected(index: Int) {
        _selectedTab.value = index
    }
}