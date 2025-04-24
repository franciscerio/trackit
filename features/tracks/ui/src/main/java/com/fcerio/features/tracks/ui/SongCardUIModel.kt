package com.fcerio.features.tracks.ui

data class SongCardUIModel(
    val id: Long,
    val imageURL: String,
    val price: Double,
    val songTitle: String,
    val songArtist: String,
    val shortDescription: String,
    val longDescription: String,
    val isFavorite: Boolean
) {

    override fun toString(): String {
        return "Song title = $songTitle | Song artist = $songArtist"
    }
}
