package com.fcerio.features.tracks.network.remote

import com.fcerio.core.domain.Paging
import com.fcerio.core.network.base.BaseRemoteSource
import com.fcerio.core.network.toDomain
import com.fcerio.features.tracks.domain.Track
import com.fcerio.features.tracks.network.TracksApiServices
import com.fcerio.features.tracks.network.models.payload.builder.TrackFilter
import com.fcerio.features.tracks.network.models.toDomain
import javax.inject.Inject

internal class TrackRemoteSourceImpl @Inject constructor(
    private val apiServices: TracksApiServices
) : BaseRemoteSource(), TrackRemoteSource {

    override suspend fun getTracks(token: String, payload: TrackFilter): Paging<List<Track>> {
        val response = apiServices.getTracks(
            token,
            payload
        )
        val items = response.successOr(emptyList()).map { it.toDomain() }
        return Paging(data = items, meta = response.meta.toDomain())
    }

    override suspend fun getTrackDetails(token: String, trackId: Long): Track {
        return apiServices.getTrackDetails(token, trackId)
            .data.toDomain()
    }

    override suspend fun getRelatedTracks(token: String, trackId: Long): List<Track> {
        return apiServices.getRelatedTracks(token, trackId)
            .successOr(emptyList())
            .map { it.toDomain() }
    }

    override suspend fun addTrackToFavorite(token: String, trackId: Long): Boolean {
        return apiServices.addTrackToFavorite(token, trackId)
            .success
    }

    override suspend fun unFavoriteTrack(token: String, trackId: Long): Boolean {
        return apiServices.unFavoriteTrack(token, trackId)
            .success
    }
}
