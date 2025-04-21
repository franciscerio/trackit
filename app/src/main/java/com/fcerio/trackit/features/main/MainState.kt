package com.fcerio.trackit.features.main

import com.fcerio.core.common.android.base.Event
import com.fcerio.core.common.android.base.UiStateWithId
import com.fcerio.core.common.android.base.UiStateWithIdDelegate
import com.fcerio.trackit.navigation.HomeSections


data class MainState(
    val tabs: List<HomeSections>,
    val favoriteBadgeCount: Int,
    val selectedTab: HomeSections,
    val navigateToSelectedTab: Event<HomeSections>
) : UiStateWithId by UiStateWithIdDelegate()

sealed interface MainAction {
    data object SubscribeToFavoriteTrack : MainAction
    data object LoadAllFavoriteTracks : MainAction
    data object LoadAllTabs : MainAction
    data class SelectedTab(val tab: HomeSections) : MainAction
}

sealed interface MainResult {
    data class FavoriteTracksLoaded(val tracks: List<Track>) : MainResult
    data class TabsLoaded(val tabs: List<HomeSections>) : MainResult
    data class NavigateToSelectedTab(val tab: HomeSections) : MainResult
}
