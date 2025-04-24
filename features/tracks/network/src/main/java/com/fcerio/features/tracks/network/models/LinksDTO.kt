package com.fcerio.features.tracks.network.models

import com.fcerio.features.tracks.domain.Links
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LinksDTO(
    @SerialName("first") val first: String? = null,
    @SerialName("last") val last: String? = null,
    @SerialName("next") val next: String? = null,
    @SerialName("prev") @Contextual val prev: Any? = null
)

internal fun LinksDTO?.toDomain(): Links {
    return with(this) {
        if (this == null) {
            Links.empty()
        } else {
            Links(
                first = first.orEmpty(),
                last = last.orEmpty(),
                next = next.orEmpty(),
                prev = prev ?: Any()
            )
        }
    }
}
