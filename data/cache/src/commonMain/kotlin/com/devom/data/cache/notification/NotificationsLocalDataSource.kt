package com.devom.data.cache.notification

import com.devom.data.cache.CacheHelper
import com.devom.data.cache.utils.jsonConfig
import com.devom.data.cache.utils.toCacheResult
import com.devom.models.notification.GetNotificationResponse
import com.devom.utils.network.ResponseResult
import kotlinx.coroutines.flow.Flow

interface NotificationsLocalDataSource {
    suspend fun getNotifications(userId: String): Flow<ResponseResult<List<GetNotificationResponse>>>
    suspend fun saveNotifications(userId: String, data: List<GetNotificationResponse>)
}

class NotificationsLocalDataSourceImpl : NotificationsLocalDataSource {
    private val cacheHelper = CacheHelper
    override suspend fun getNotifications(userId: String): Flow<ResponseResult<List<GetNotificationResponse>>> =
        cacheHelper.getData("getNotifications$userId").toCacheResult()

    override suspend fun saveNotifications(
        userId: String,
        data: List<GetNotificationResponse>,
    ) {
        cacheHelper.saveData("getNotifications$userId", jsonConfig.encodeToString(data))
    }
}