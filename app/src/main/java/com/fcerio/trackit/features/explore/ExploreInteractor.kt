package com.fcerio.trackit.features.explore

import com.fcerio.core.common.android.base.Interactor
import com.fcerio.features.tracks.data.TrackRepository
import com.fcerio.features.tracks.network.models.payload.builder.TrackFilter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import javax.inject.Inject

class ExploreInteractor @Inject constructor(
    private val trackRepository: TrackRepository
) : Interactor<ExploreAction, ExploreResult> {

    private fun loadTracks(filter: TrackFilter.Builder): Flow<ExploreResult> {
        return flow {
            trackRepository.loadTracks(filter)
            emit(ExploreResult.InitializeTracksSuccess)
        }
    }

    private fun subscribeToTracks(): Flow<ExploreResult> {
        return trackRepository.getTracksFlow()
            .map {
                ExploreResult.TracksLoaded(it)
            }
    }

    private fun loadNewTracks(): Flow<ExploreResult> {
        return flow {
            emit(ExploreResult.PulledToRefresh)
            trackRepository.clearTracks()
            trackRepository.loadTracks(TrackFilter.default())
            emit(ExploreResult.PulledToRefreshSuccess)
        }
    }

    override fun actionProcessor(actions: Flow<ExploreAction>): Flow<ExploreResult> {
        return merge(
            actions.filterIsInstance<ExploreAction.SubscribeToTrack>().flatMapLatest { subscribeToTracks() },
            actions.filterIsInstance<ExploreAction.PulledToRefresh>().flatMapLatest { loadNewTracks() }
        )
    }
}
