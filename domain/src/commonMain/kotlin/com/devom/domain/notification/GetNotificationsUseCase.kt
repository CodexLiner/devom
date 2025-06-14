package com.devom.domain.notification

import com.devom.data.repository.notification.NotificationRepository
import com.devom.data.repository.notification.NotificationRepositoryImpl
import com.devom.models.notification.GetNotificationResponse
import com.devom.network.getUser
import com.devom.utils.cachepolicy.CachePolicy
import com.devom.utils.network.ResponseResult
import kotlinx.coroutines.flow.Flow

class GetNotificationsUseCase {
    private val notificationRepository: NotificationRepository = NotificationRepositoryImpl()
    suspend operator fun invoke(
        cachePolicy: CachePolicy = CachePolicy.CacheAndNetwork,
    ): Flow<ResponseResult<List<GetNotificationResponse>>> {
       return notificationRepository.getNotifications(getUser().userId.toString(), cachePolicy)
    }
}