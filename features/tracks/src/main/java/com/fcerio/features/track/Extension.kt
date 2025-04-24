package com.fcerio.features.track

import com.fcerio.features.tracks.domain.Track
import com.fcerio.features.tracks.ui.SongCardUIModel

fun Track.toMapUIModel(): SongCardUIModel {
    return SongCardUIModel(
        id = trackId,
        imageURL = artworkUrl100,
        songArtist = artistName,
        songTitle = trackName,
        price = trackPrice,
        shortDescription = shortDescription,
        longDescription = longDescription,
        isFavorite = isFavorite
    )
}
