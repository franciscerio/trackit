package com.fcerio.core.network.base

import com.fcerio.core.network.MetaDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class BasePagingResponse<T>(
    @SerialName("data") val data: T,
    @SerialName("success") val success: Boolean = false,
    @SerialName("message") val message: String = "",
    @SerialName("meta") val meta: MetaDTO,
    @SerialName("http_status") val httpStatus: Int = 500
) {

    fun isSuccessful(): Boolean = httpStatus == 200 || httpStatus == 201
}
