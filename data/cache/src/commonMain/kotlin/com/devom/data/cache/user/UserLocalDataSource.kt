package com.devom.data.cache.user

import com.devom.data.cache.CacheHelper
import com.devom.data.cache.utils.jsonConfig
import com.devom.data.cache.utils.toCacheResult
import com.devom.models.auth.UserResponse
import com.devom.utils.network.ResponseResult
import kotlinx.coroutines.flow.Flow

interface UserLocalDataSource {
    suspend fun getUserProfile() : Flow<ResponseResult<UserResponse>>
    suspend fun saveUserProfile(data : UserResponse)
}
class UserLocalDataSourceImpl : UserLocalDataSource {
    val cacheHelper = CacheHelper
    override suspend fun getUserProfile(): Flow<ResponseResult<UserResponse>>  = cacheHelper.getData("getUserProfile").toCacheResult()
    override suspend fun saveUserProfile(data: UserResponse) {
        cacheHelper.saveData("getUserProfile" , jsonConfig.encodeToString(data))
    }
}