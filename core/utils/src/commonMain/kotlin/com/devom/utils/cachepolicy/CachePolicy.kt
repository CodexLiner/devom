package com.devom.utils.cachepolicy

enum class CachePolicy(val value:String){
    CacheFirst("CacheFirst"),
    CacheOnly("CacheOnly"),
    NetworkFirst("NetworkFirst"),
    NetworkOnly("NetworkOnly"),
    CacheAndNetwork("CacheAndNetwork"),
}