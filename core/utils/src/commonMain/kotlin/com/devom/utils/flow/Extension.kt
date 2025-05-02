package com.devom.utils.flow

import com.devom.utils.network.ResponseResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlin.experimental.ExperimentalTypeInference

fun <T> apiFlow(call: suspend () -> Flow<ResponseResult<T>>): Flow<ResponseResult<T>> = flow {
    emit(ResponseResult.Loading)
    emitAll(call())
}
