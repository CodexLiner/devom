package com.devom.app.ui.screens.rituals

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devom.Project
import com.devom.models.auth.UserResponse
import com.devom.models.pandit.GetPanditPoojaResponse
import com.devom.models.pooja.GetPoojaItemResponse
import com.devom.utils.network.onResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class RitualsViewModel : ViewModel() {
    private val _user = MutableStateFlow<UserResponse?>(null)
    val user = _user

    private val _rituals = MutableStateFlow<List<GetPanditPoojaResponse>?>(null)
    val rituals = _rituals

    fun getUserProfile() {
        viewModelScope.launch {
            Project.user.getUserProfileUseCase.invoke().collect {
                it.onResult {
                    _user.value = it.data
                    getRituals()
                }
            }
        }
    }

    fun getRituals() {
        viewModelScope.launch {
            Project.pandit.getPanditPoojaUseCase.invoke(user.value?.userId.toString()).collect {
                it.onResult {
                    _rituals.value = it.data
                }
            }
        }
    }
}
