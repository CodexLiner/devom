package com.devom.utils

import kotlinx.coroutines.flow.MutableStateFlow

object Application {
    private val _loaderState = MutableStateFlow(false)
    val loaderState = _loaderState

    private var _toastState = MutableStateFlow<String?>(null)
    val toastState = _toastState

    private val _loginState = MutableStateFlow(true)
    val loginState = _loginState


    fun showLoader() {
        _loaderState.value = true
    }

    fun hideLoader() {
        _loaderState.value = false
    }

    fun showToast(message: String) {
        _toastState.value = message
    }

    fun hideToast() {
        _toastState.value = null
    }

    fun logout() {
        _loginState.value = false
    }

    fun isLoggedIn(isLoggedIn: Boolean) {
        _loginState.value = isLoggedIn
    }

}