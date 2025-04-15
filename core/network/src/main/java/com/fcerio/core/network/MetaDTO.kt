package com.fcerio.core.network

import com.fcerio.core.domain.Meta
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MetaDTO(
    @SerialName("current_page")
    val currentPage: Int,
    @SerialName("from")
    val from: Int,
    @SerialName("last_page")
    val lastPage: Int,
    @SerialName("per_page")
    val perPage: Int,
    @SerialName("to")
    val to: Int,
    @SerialName("total")
    val total: Int
)

fun MetaDTO.toDomain(): Meta {
    return Meta(
        currentPage,
        from,
        lastPage,
        perPage,
        to,
        total
    )
}
