package com.fcerio.features.tracks.network.models

import com.fcerio.features.tracks.domain.Link
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LinkDTO(
    @SerialName("active") val active: Boolean? = null,
    @SerialName("label") val label: String? = null,
    @SerialName("url") val url: String? = null
)

internal fun LinkDTO?.toDomain(): Link {
    return with(this) {
        if (this == null) {
            Link.empty()
        } else {
            Link(
                active = active ?: false,
                label = label.orEmpty(),
                url = url.orEmpty()
            )
        }
    }
}
