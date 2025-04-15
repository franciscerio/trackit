package com.fcerio.features.tracks.network.remote

import com.fcerio.core.domain.Paging
import com.fcerio.features.tracks.domain.Track
import com.fcerio.features.tracks.network.models.payload.builder.TrackFilter

interface TrackRemoteSource {

    suspend fun getTracks(token: String, payload: TrackFilter): Paging<List<Track>>

    suspend fun getTrackDetails(token: String, trackId: Long): Track

    suspend fun getRelatedTracks(token: String, trackId: Long): List<Track>

    suspend fun addTrackToFavorite(token: String, trackId: Long): Boolean

    suspend fun unFavoriteTrack(token: String, trackId: Long): Boolean
}
