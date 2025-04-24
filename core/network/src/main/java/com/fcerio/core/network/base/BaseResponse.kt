package com.fcerio.core.network.base

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class BaseResponse<T>(
    @SerialName("data") val data: T,
    val success: Boolean = false,
    val message: String = "",
    @SerialName("http_status") val httpStatus: Int = 500
) {

    fun isSuccessful(): Boolean = httpStatus == 200 || httpStatus == 201
}
