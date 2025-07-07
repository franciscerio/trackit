package com.fcerio.trackit.features.explore

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import com.fcerio.core.common.android.base.BaseViewModel
import com.fcerio.features.track.toMapUIModel
import com.fcerio.features.tracks.data.TrackPagingSource
import com.fcerio.features.tracks.data.TrackRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    interactor: ExploreInteractor,
    private val trackRepository: TrackRepository
) : BaseViewModel<ExploreAction, ExploreResult, ExploreState>(interactor) {

    private val search = MutableStateFlow("")

    val pager by lazy {
        Pager(
            config = PagingConfig(
                pageSize = 10,
                prefetchDistance = 1,
                initialLoadSize = 10
            ),
            pagingSourceFactory = {
                TrackPagingSource(
                    trackRepository,
                    search.value
                )
            }
        ).flow.map { it.map { it.toMapUIModel() } }.cachedIn(viewModelScope)
    }

    init {
        postAction(ExploreAction.SubscribeToTrack)
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

    override fun stateReducer(): (ExploreState, ExploreResult) -> ExploreState =
        { prevState, result ->
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
