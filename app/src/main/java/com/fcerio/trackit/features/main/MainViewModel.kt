package com.fcerio.trackit.features.main

import com.fcerio.core.common.android.base.BaseViewModel
import com.fcerio.core.common.android.base.Event
import com.fcerio.trackit.navigation.HomeSections
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    interactor: MainInteractor
) : BaseViewModel<MainAction, MainResult, MainState>(interactor) {

    init {
        postAction(MainAction.LoadAllTabs)
        postAction(MainAction.SubscribeToFavoriteTrack)
        postAction(MainAction.LoadAllFavoriteTracks)
    }

    override val defaultState: MainState
        get() = MainState(
            tabs = emptyList(),
            favoriteBadgeCount = 0,
            selectedTab = HomeSections.HOME,
            navigateToSelectedTab = Event.empty()
        )

    fun selectedTab(tab: HomeSections) {
        postAction(MainAction.SelectedTab(tab))
    }

    override fun stateReducer(): (MainState, MainResult) -> MainState = { prevState, result ->
        when (result) {
            is MainResult.FavoriteTracksLoaded -> prevState.copy(favoriteBadgeCount = result.tracks.count())
            is MainResult.TabsLoaded -> prevState.copy(tabs = result.tabs)
            is MainResult.NavigateToSelectedTab -> prevState.copy(
                selectedTab = result.tab,
                navigateToSelectedTab = Event(result.tab)
            )
        }
    }
}
