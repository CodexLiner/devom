package com.devom.app.ui.screens.addslot

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devom.Project
import com.devom.models.auth.UserResponse
import com.devom.models.slots.CreatePanditSlotInput
import com.devom.models.slots.Slot
import com.devom.utils.network.onResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CreateSlotViewModel : ViewModel() {

    private val _user = MutableStateFlow<UserResponse?>(null)
    val user = _user

    private val _slots = MutableStateFlow(listOf<Slot>())
    val slots = _slots

    init {
        getUserProfile()
    }

    fun getUserProfile() {
        viewModelScope.launch {
            Project.user.getUserProfileUseCase.invoke().collect {
                it.onResult {
                    _user.value = it.data
                }
            }
        }
    }

    fun getAvailableSlots() {
        viewModelScope.launch {
            Project.pandit.getAvailableSlotsUseCase.invoke(user.value?.userId.toString()).collect {
                it.onResult {
                    _slots.value = it.data.map {
                        Slot(
                            availableDate = it.availableDate,
                            startTime = it.startTime,
                            endTime = it.endTime
                        )
                    }
                }
            }
        }
    }

    fun setAvailableSlots(slots: List<Slot>) {
        _slots.value = slots
    }

    fun createPanditSlot(slots: List<Slot>) {
        viewModelScope.launch {
            Project.pandit.createPanditSlotUseCase.invoke(
                CreatePanditSlotInput(
                    panditId = user.value?.userId ?: return@launch,
                    slots = slots
                )
            ).collect {
                it.onResult {
                    setAvailableSlots(slots)
                }
            }
        }
    }
}