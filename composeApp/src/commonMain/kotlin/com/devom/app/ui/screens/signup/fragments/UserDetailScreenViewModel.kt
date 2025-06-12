package com.devom.app.ui.screens.signup.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devom.Project
import com.devom.models.auth.UserRequestResponse
import com.devom.models.other.City
import com.devom.models.other.Country
import com.devom.models.other.State
import com.devom.utils.network.onResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserDetailScreenViewModel : ViewModel() {
    private val _countryList =  MutableStateFlow<List<Country>>(emptyList())
    val countryList = _countryList.asStateFlow()

    private val _stateList =  MutableStateFlow<List<State>>(emptyList())
    val stateList = _stateList.asStateFlow()

    private val _cityList =  MutableStateFlow<List<City>>(emptyList())
    val cityList = _cityList.asStateFlow()

    val selectedCountry = MutableStateFlow<Country?>(null)
    val selectedState = MutableStateFlow<State?>(null)
    val selectedCity = MutableStateFlow<City?>(null)


    init {
        getCountryList()
        getStateList()
    }

    fun setInitialValues(userResponse: UserRequestResponse) {
        viewModelScope.launch {
            selectedCountry.value = countryList.value.find { it.name == userResponse.country }
            selectedState.value = stateList.value.find { it.name == userResponse.state }
            selectedCity.value = cityList.value.find { it.name == userResponse.city }
        }
    }

    private fun getCountryList() {
        viewModelScope.launch {
            Project.other.getAllCountriesUseCase.invoke().collect {
                it.onResult {
                    _countryList.value = it.data
                }
            }
        }
    }

    fun getStateList(countryCode: String = "IN") {
        viewModelScope.launch {
            Project.other.getAllStatesUseCase.invoke(countryCode).collect {
                it.onResult {
                    _countryList.value = it.data
                }
            }
        }
    }

    fun getCityList(countryCode: String = "IN", stateCode: String) {
        viewModelScope.launch {
            Project.other.getAllCitiesUseCase.invoke(countryCode, stateCode).collect {
                it.onResult {
                    _cityList.value = it.data
                }
            }
        }
    }
}