package com.fcerio.features.tracks.domain

data class Artwork(
    val collectionName: String,
    val createdAt: String,
    val fileName: String,
    val id: Long,
    val mimeType: String,
    val name: String,
    val responsiveUrl: String,
    val size: Int,
    val thumbUrl: String,
    val url: String
) {
    companion object {
        fun empty(): Artwork {
            return Artwork(
                "", "", "", 0L, "",
                "", "", 0, "", ""
            )
        }
    }
}
