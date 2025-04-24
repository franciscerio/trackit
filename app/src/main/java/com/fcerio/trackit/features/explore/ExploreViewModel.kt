package com.fcerio.trackit.features.explore

import com.fcerio.core.common.android.base.BaseViewModel
import com.fcerio.features.track.toMapUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    interactor: ExploreInteractor
) : BaseViewModel<ExploreAction, ExploreResult, ExploreState>(interactor) {

    init {
        postAction(ExploreAction.SubscribeToTrack)
        postAction(ExploreAction.LoadInitializeTracks)
    }

    override val defaultState: ExploreState
        get() = ExploreState(
            tracks = emptyList(),
            isLoading = true,
            isRefreshing = false
        )

    fun pulledToRefresh() {
        postAction(ExploreAction.PulledToRefresh)
    }

    override fun stateReducer(): (ExploreState, ExploreResult) -> ExploreState = { prevState, result ->
        when (result) {
            is ExploreResult.TracksLoaded -> prevState.copy(tracks = result.tracks.map { it.toMapUIModel() })
            is ExploreResult.PulledToRefreshSuccess -> {
                prevState.copy(
                    isRefreshing = false
                )
            }

            is ExploreResult.PulledToRefresh -> prevState.copy(isRefreshing = true)
            is ExploreResult.InitializeTracksSuccess -> {
                prevState.copy(isLoading = false)
            }
        }
    }
}
