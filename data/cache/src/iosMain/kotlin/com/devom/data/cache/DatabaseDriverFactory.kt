package com.devom.data.cache

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.devom.LocalCacheDatabase

actual class DatabaseDriverFactory {

    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(
            schema = LocalCacheDatabase.Schema,
            name = "LocalCacheDatabase.db"
        )
    }
}