package com.fcerio.trackit.features.explore

import com.fcerio.core.common.android.base.UiStateWithId
import com.fcerio.core.common.android.base.UiStateWithIdDelegate
import com.fcerio.features.tracks.domain.Track
import com.fcerio.features.tracks.ui.SongCardUIModel

data class ExploreState(
    val tracks: List<SongCardUIModel>,
    val isLoading: Boolean,
    val isRefreshing: Boolean
) : UiStateWithId by UiStateWithIdDelegate()

sealed interface ExploreAction {
    data object SubscribeToTrack : ExploreAction
    data object PulledToRefresh : ExploreAction
}

sealed interface ExploreResult {
    data class TracksLoaded(val tracks: List<Track>) : ExploreResult
    data object InitializeTracksSuccess : ExploreResult
    data object PulledToRefresh : ExploreResult
    data object PulledToRefreshSuccess : ExploreResult
}
