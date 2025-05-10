package com.devom.data.cache

import com.devom.LocalCacheDatabase

object CacheHelper {

    private val database get() = LocalCacheDatabase(DatabaseDriverFactory().createDriver())
    private val queries get() = database.localCacheDatabaseQueries

    fun saveData(methodName: String, content: String) {
        queries.insertLocalData(methodName, content)
    }

    fun getData(methodName: String): String? {
        return queries.getLocalDataById(methodName).executeAsOneOrNull()?.content
    }
}
