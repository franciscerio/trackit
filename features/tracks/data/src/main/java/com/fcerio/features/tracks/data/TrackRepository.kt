package com.fcerio.features.tracks.data

import com.fcerio.core.domain.Paging
import com.fcerio.features.tracks.domain.Track
import com.fcerio.features.tracks.network.models.payload.builder.TrackFilter
import kotlinx.coroutines.flow.Flow

interface TrackRepository {

    suspend fun loadTracks(filter: TrackFilter.Builder): Paging<List<Track>>

    suspend fun clearTracks(): Boolean

    suspend fun loadFavoriteTracks(limit: Int? = null)

    suspend fun loadTrackDetails(trackId: Long): Track

    suspend fun getRelatedTracks(trackId: Long)

    suspend fun addTrackToFavorite(trackId: Long): Boolean

    suspend fun unFavoriteTrack(trackId: Long): Boolean

    fun getTracksFlow(): Flow<List<Track>>

    fun getFavoriteTracksFlow(): Flow<List<Track>>

    fun observeRelatedTracks(): Flow<List<Track>>
}