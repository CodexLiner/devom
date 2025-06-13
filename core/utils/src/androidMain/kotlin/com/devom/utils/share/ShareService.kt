package com.devom.utils.share

import android.content.Context
import android.content.Intent
import androidx.startup.Initializer

object UtilsApp {
    lateinit var applicationContext: Context
    fun init(context: Context) {
        applicationContext = context
    }
}

class AppInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        UtilsApp.init(context)
    }

    override fun dependencies(): List<Class<out Initializer<*>?>?> = listOf()
}

class AndroidShareService() : ShareService {
    override fun share(title: String, url: String) {
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, title)
            putExtra(Intent.EXTRA_TEXT, "$title\n$url")
        }
        UtilsApp.applicationContext.startActivity(
            Intent.createChooser(shareIntent, "Share via").apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            })
    }
}

actual class ShareServiceProvider {
    actual fun getShareService(): ShareService = AndroidShareService()
}