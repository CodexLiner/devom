package com.devom.domain.notification

import com.devom.data.repository.notification.NotificationRepository
import com.devom.data.repository.notification.NotificationRepositoryImpl
import com.devom.utils.cachepolicy.CachePolicy

class GetNotificationsUseCase {
    private val notificationRepository: NotificationRepository = NotificationRepositoryImpl()
    suspend operator fun invoke(
        userId: String,
        cachePolicy: CachePolicy = CachePolicy.CacheAndNetwork,
    ) = notificationRepository.getNotifications(userId, cachePolicy)
}