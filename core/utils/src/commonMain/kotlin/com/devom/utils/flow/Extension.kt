package com.devom.utils.flow

import com.devom.utils.cachepolicy.CachePolicy
import com.devom.utils.network.ResponseResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

fun <T> apiFlow(call: suspend () -> Flow<ResponseResult<T>>): Flow<ResponseResult<T>> = flow {
    emit(ResponseResult.Loading)
    emitAll(call())
}.flowOn(Dispatchers.IO)


inline fun <T> cacheAwareFlow(
    cachePolicy: CachePolicy,
    crossinline fetchCache: suspend () -> Flow<ResponseResult<T>>,
    crossinline saveCache: suspend (T) -> Unit = {},
    crossinline fetchNetwork: suspend () -> Flow<ResponseResult<T>>,
): Flow<ResponseResult<T>> = flow {

    // --- Emit loading initially ---
    emit(ResponseResult.Loading)

    when (cachePolicy) {
        CachePolicy.CacheOnly -> emitAll(fetchCache())

        CachePolicy.NetworkOnly -> fetchNetwork().collect { result ->
            emit(result)
            if (result is ResponseResult.Success) saveCache(result.data)
        }


        CachePolicy.CacheFirst -> {
            var cacheSucceeded = false
            var cacheEmittedAny = false

            fetchCache().collect { result ->
                cacheEmittedAny = true
                emit(result)
                if (result is ResponseResult.Success) {
                    cacheSucceeded = true
                }
            }

            if (!cacheSucceeded) {
                fetchNetwork().collect { result ->
                    emit(result)
                    if (result is ResponseResult.Success) saveCache(result.data)
                }
            } else if (!cacheEmittedAny) {
                fetchNetwork().collect { result ->
                    emit(result)
                    if (result is ResponseResult.Success) saveCache(result.data)
                }
            }
        }


        CachePolicy.NetworkFirst -> {
            var networkFailed = false
            fetchNetwork().collect { result ->
                emit(result)
                if (result is ResponseResult.Success) {
                    saveCache(result.data)
                } else if (result is ResponseResult.Error) {
                    networkFailed = true
                }
            }

            if (networkFailed) {
                emitAll(fetchCache())
            }
        }

        CachePolicy.CacheAndNetwork -> {
            fetchCache().collect { emit(it) }
            fetchNetwork().collect { result ->
                emit(result)
                if (result is ResponseResult.Success) saveCache(result.data)
            }
        }
    }
}.flowOn(Dispatchers.IO)
