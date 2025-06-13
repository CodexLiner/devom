package com.devom.utils.share

interface ShareService {
    fun share(title: String, url: String)
}

expect class ShareServiceProvider() {
    fun getShareService(): ShareService
}
fun shareContent(serviceProvider: ShareServiceProvider, title: String, url: String) {
    val shareService = serviceProvider.getShareService()
    shareService.share(title, url)
}