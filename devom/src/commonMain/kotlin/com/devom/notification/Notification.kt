package com.devom.notification

class Notification {
    val getNotificationsUseCase by lazy { com.devom.domain.notification.GetNotificationsUseCase() }
}