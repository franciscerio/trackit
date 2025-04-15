package com.fcerio.features.tracks.network

import com.fcerio.core.network.base.BasePagingResponse
import com.fcerio.core.network.base.BaseResponse
import com.fcerio.core.network.base.BasicResponse
import com.fcerio.features.tracks.network.models.TrackDTO
import com.fcerio.features.tracks.network.models.payload.builder.TrackFilter

interface TracksApiServices {
    suspend fun getTracks(token: String, payload: TrackFilter): BasePagingResponse<List<TrackDTO>>

    suspend fun getTrackDetails(token: String, trackId: Long): BaseResponse<TrackDTO>

    suspend fun getRelatedTracks(token: String, trackId: Long): BaseResponse<List<TrackDTO>>

    suspend fun addTrackToFavorite(token: String, trackId: Long): BasicResponse

    suspend fun unFavoriteTrack(token: String, trackId: Long): BasicResponse
}