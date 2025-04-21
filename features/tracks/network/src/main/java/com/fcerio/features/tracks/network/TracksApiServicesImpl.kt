package com.fcerio.features.tracks.network

import com.fcerio.core.network.base.BasePagingResponse
import com.fcerio.core.network.base.BaseResponse
import com.fcerio.core.network.base.BasicResponse
import com.fcerio.features.tracks.network.models.TrackDTO
import com.fcerio.features.tracks.network.models.payload.builder.TrackFilter
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.http.HttpHeaders
import io.ktor.http.appendPathSegments
import io.ktor.http.parametersOf
import javax.inject.Inject

internal class TracksApiServicesImpl @Inject constructor(
    private val httpClient: HttpClient
) : TracksApiServices {

    override suspend fun getTracks(
        token: String,
        payload: TrackFilter
    ): BasePagingResponse<List<TrackDTO>> {
        val response = httpClient.get("/tracks") {
            headers {
                append(HttpHeaders.Authorization, token)
            }

            url {
                parametersOf(payload.toMap())
            }
        }.body<BasePagingResponse<List<TrackDTO>>>()

        return response
    }

    override suspend fun getTrackDetails(token: String, trackId: Long): BaseResponse<TrackDTO> {
        val response = httpClient.get("/tracks") {
            headers {
                append(HttpHeaders.Authorization, token)
            }
            url {
                appendPathSegments(trackId.toString())
            }
        }.body<BaseResponse<TrackDTO>>()
        return response
    }

    override suspend fun getRelatedTracks(
        token: String,
        trackId: Long
    ): BaseResponse<List<TrackDTO>> {
        val response = httpClient.get("/tracks") {
            headers {
                append(HttpHeaders.Authorization, token)
            }
            url {
                appendPathSegments(trackId.toString(), "related")
            }
        }.body<BasePagingResponse<List<TrackDTO>>>()
        return response
    }

    override suspend fun addTrackToFavorite(token: String, trackId: Long): BasicResponse {
        val response = httpClient.post("/tracks") {
            headers {
                append(HttpHeaders.Authorization, token)
            }
            url {
                appendPathSegments(trackId.toString(), "favorite")
            }
        }.body<BasicResponse>()
        return response
    }

    override suspend fun unFavoriteTrack(token: String, trackId: Long): BasicResponse {
        val response = httpClient.post("/tracks") {
            headers {
                append(HttpHeaders.Authorization, token)
            }
            url {
                appendPathSegments(trackId.toString(), "unfavorite")
            }
        }.body<BasicResponse>()
        return response
    }
}