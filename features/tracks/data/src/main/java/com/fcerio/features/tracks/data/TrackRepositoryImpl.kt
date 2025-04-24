package com.fcerio.features.tracks.data

import com.fcerio.core.domain.Paging
import com.fcerio.core.domain.SessionProvider
import com.fcerio.features.tracks.domain.Track
import com.fcerio.features.tracks.network.models.payload.builder.TrackFilter
import com.fcerio.features.tracks.network.remote.TrackRemoteSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

internal class TrackRepositoryImpl @Inject constructor(
    private val remote: TrackRemoteSource,
    private val provider: SessionProvider
) : TrackRepository {

    private val tracks = MutableStateFlow<List<Track>>(emptyList())
    private val favoriteTracks = MutableStateFlow<List<Track>>(emptyList())
    private val relatedTracks = MutableStateFlow<List<Track>>(emptyList())

    override suspend fun loadTracks(filter: TrackFilter.Builder): Paging<List<Track>> {
        val response = remote.getTracks(
            provider.getAccessToken(),
            filter.build()
        )
        tracks.tryEmit(response.data)
        return response
    }

    override suspend fun clearTracks(): Boolean {
        return tracks.tryEmit(emptyList())
    }

    override suspend fun loadFavoriteTracks(limit: Int?) {
        val response = remote.getTracks(
            provider.getAccessToken(),
            TrackFilter.Builder()
                .favorite(true)
                .limit(
                    // Can be improve, soon!
                    if (limit == null || limit == 0) {
                        Int.MAX_VALUE
                    } else {
                        limit
                    }
                )
                .build()
        )

        favoriteTracks.tryEmit(
            response.data
        )
    }

    override suspend fun loadTrackDetails(trackId: Long): Track {
        return remote.getTrackDetails(provider.getAccessToken(), trackId)
    }

    override suspend fun getRelatedTracks(trackId: Long) {
        val items = remote.getRelatedTracks(provider.getAccessToken(), trackId)
        relatedTracks.tryEmit(items)
    }

    override suspend fun addTrackToFavorite(trackId: Long): Boolean {
        return remote.addTrackToFavorite(provider.getAccessToken(), trackId)
    }

    override suspend fun unFavoriteTrack(trackId: Long): Boolean {
        return remote.unFavoriteTrack(provider.getAccessToken(), trackId)
    }

    override fun getTracksFlow(): Flow<List<Track>> = tracks.asStateFlow()

    override fun getFavoriteTracksFlow(): Flow<List<Track>> = favoriteTracks.asStateFlow()

    override fun observeRelatedTracks(): Flow<List<Track>> {
        return relatedTracks.asStateFlow()
    }
}
