package com.devom

class Other {
    val getAllCountriesUseCase by lazy { com.devom.domain.other.GetAllCountriesUseCase() }
    val getAllStatesUseCase by lazy { com.devom.domain.other.GetAllStatesUseCase() }
    val getAllCitiesUseCase by lazy { com.devom.domain.other.GetAllCitiesUseCase() }
}