package com.fcerio.core.network.base

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

open class BaseRemoteSource {
    fun getJsonRequestBody(jsonString: String) =
        jsonString.toRequestBody("application/json".toMediaTypeOrNull())

    fun <T> BaseResponse<T>.successOr(fallback: T): T {
        return (this as? BaseResponse<T>)?.data ?: fallback
    }
}
