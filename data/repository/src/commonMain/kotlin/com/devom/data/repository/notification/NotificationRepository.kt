package com.devom.data.repository.notification

import com.devom.data.cache.notification.NotificationsLocalDataSource
import com.devom.data.cache.notification.NotificationsLocalDataSourceImpl
import com.devom.data.server.notification.NotificationsRemoteDataSource
import com.devom.data.server.notification.NotificationsRemoteDataSourceImpl
import com.devom.models.notification.GetNotificationResponse
import com.devom.utils.cachepolicy.CachePolicy
import com.devom.utils.flow.cacheAwareFlow
import com.devom.utils.network.ResponseResult
import kotlinx.coroutines.flow.Flow

interface NotificationRepository {
    suspend fun getNotifications(
        userId: String,
        cachePolicy: CachePolicy = CachePolicy.CacheAndNetwork,
    ): Flow<ResponseResult<List<GetNotificationResponse>>>
}

class NotificationRepositoryImpl : NotificationRepository {
    private val remoteDataSource: NotificationsRemoteDataSource =
        NotificationsRemoteDataSourceImpl()
    private val localDataSource: NotificationsLocalDataSource = NotificationsLocalDataSourceImpl()
    override suspend fun getNotifications(
        userId: String,
        cachePolicy: CachePolicy,
    ): Flow<ResponseResult<List<GetNotificationResponse>>> =
        cacheAwareFlow(
            cachePolicy = cachePolicy,
            fetchNetwork = { remoteDataSource.getNotifications(userId) },
            fetchCache = { localDataSource.getNotifications(userId) },
            saveCache = { localDataSource.saveNotifications(userId, it) })
}