package com.devom.data.server.notification

import com.devom.data.server.endpoints.NotificationsEndpoints
import com.devom.models.notification.GetNotificationResponse
import com.devom.network.NetworkClient
import com.devom.network.utils.toResponseResult
import com.devom.utils.network.ResponseResult
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow

interface NotificationsRemoteDataSource {
    suspend fun getNotifications(userId : String): Flow<ResponseResult<List<GetNotificationResponse>>>
}
class NotificationsRemoteDataSourceImpl() : NotificationsRemoteDataSource {
    private val ktorClient = NetworkClient.ktorClient
    override suspend fun getNotifications(userId: String): Flow<ResponseResult<List<GetNotificationResponse>>> =
        ktorClient.get(NotificationsEndpoints.GetNotifications.plus("/$userId")).toResponseResult()

}