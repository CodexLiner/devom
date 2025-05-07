package com.devom.utils

import kotlinx.coroutines.flow.MutableStateFlow

object Application {
    private val _loaderState = MutableStateFlow(false)
    val loaderState = _loaderState

    private var _toastState = MutableStateFlow<String?>(null)
    val toastState = _toastState


    fun show() {
        _loaderState.value = true
    }

    fun hide() {
        _loaderState.value = false
    }

    fun showToast(message: String) {
        _toastState.value = message
    }

    fun hideToast() {
        _toastState.value = null
    }

}