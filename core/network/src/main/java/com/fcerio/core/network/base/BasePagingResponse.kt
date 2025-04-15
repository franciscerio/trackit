package com.fcerio.core.network.base

import com.fcerio.core.network.MetaDTO

class BasePagingResponse<T>(
    data: T,
    val meta: MetaDTO
) : BaseResponse<T>(data)