package com.fcerio.features.tracks.domain

data class Track(
    val artistId: Int,
    val artistName: String,
    val artistViewUrl: String,
    val artwork100: Artwork,
    val artwork30: Artwork,
    val artwork60: Artwork,
    val artworkUrl100: String,
    val artworkUrl30: String,
    val artworkUrl60: String,
    val artworkUrl600: String,
    val collectionArtistId: Int,
    val collectionArtistViewUrl: String,
    val collectionCensoredName: String,
    val collectionExplicitness: String,
    val collectionHdPrice: Double,
    val collectionId: Int,
    val collectionName: String,
    val collectionPrice: Double,
    val collectionViewUrl: String,
    val contentAdvisoryRating: String,
    val country: String,
    val createdAt: String,
    val currency: String,
    val discCount: Int,
    val discNumber: Int,
    val feedUrl: String,
    val genreIds: List<String>,
    val genres: List<String>,
    val isFavorite: Boolean,
    val isStreamable: Boolean,
    val kind: String,
    val longDescription: String,
    val preview: Preview,
    val previewUrl: String,
    val previewVideo: Preview,
    val primaryGenreName: String,
    val releaseDate: String,
    val shortDescription: String,
    val trackCensoredName: String,
    val trackCount: Int,
    val trackExplicitness: String,
    val trackHdPrice: Double,
    val trackHdRentalPrice: Double,
    val trackId: Long,
    val trackName: String,
    val trackNumber: Int,
    val trackPrice: Double,
    val trackRentalPrice: Double,
    val trackTimeMillis: Int,
    val trackViewUrl: String,
    val updatedAt: String,
    val wrapperType: String
)

fun Track?.getArtworkImageUrl(): String {
    if (this == null)
        return ""

    return artworkUrl600.ifEmpty { artworkUrl100.ifEmpty { artworkUrl60 } }
}
