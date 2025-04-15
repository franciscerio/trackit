package com.fcerio.features.tracks.network.models

import com.fcerio.features.tracks.domain.Artwork
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArtworkDTO(
    @SerialName("collection_name") val collectionName: String? = null,
    @SerialName("created_at") val createdAt: String? = null,
    @SerialName("file_name") val fileName: String? = null,
    @SerialName("id") val id: Long? = null,
    @SerialName("mime_type") val mimeType: String? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("responsive_url") val responsiveUrl: String? = null,
    @SerialName("size") val size: Int? = null,
    @SerialName("thumb_url") val thumbUrl: String? = null,
    @SerialName("url") val url: String? = null
)

internal fun ArtworkDTO?.toDomain(): Artwork {
    return if (this == null) {
        Artwork.empty()
    } else {
        with(this) {
            Artwork(
                collectionName = collectionName.orEmpty(),
                createdAt = createdAt.orEmpty(),
                fileName = fileName.orEmpty(),
                id = id ?: 0L,
                mimeType = mimeType.orEmpty(),
                name = name.orEmpty(),
                responsiveUrl = responsiveUrl.orEmpty(),
                size = size ?: 0,
                thumbUrl = thumbUrl.orEmpty(),
                url = url.orEmpty()
            )
        }
    }
}
