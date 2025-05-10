package com.devom.data.cache

import android.content.Context
import androidx.startup.Initializer
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.devom.LocalCacheDatabase

object CacheApp {
    lateinit var applicationContext: Context
    fun init(context: Context) {
        applicationContext = context
    }
}

class AppInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        CacheApp.init(context)
    }

    override fun dependencies(): List<Class<out Initializer<*>?>?> = listOf()
}

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(
            schema = LocalCacheDatabase.Schema,
            context = CacheApp.applicationContext,
            name = "LocalCacheDatabase.db"
        )
    }
}