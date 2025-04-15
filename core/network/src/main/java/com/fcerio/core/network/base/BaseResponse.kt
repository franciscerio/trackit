package com.fcerio.core.network.base

open class BaseResponse<T>(
    open val data: T,
    val success: Boolean = false,
    val message: String = "",
    val httpStatus: Int = 500
) {

    fun isSuccessful(): Boolean = httpStatus == 200 || httpStatus == 201
}
