package com.fcerio.trackit.features.main

import com.fcerio.core.common.android.base.Interactor
import com.fcerio.trackit.navigation.HomeSections
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import javax.inject.Inject

class MainInteractor @Inject constructor(
    private val trackRepository: TrackRepository
) : Interactor<MainAction, MainResult> {

    private fun loadAllTabs(): Flow<MainResult> {
        return flow {
            emit(MainResult.TabsLoaded(HomeSections.entries))
        }
    }

    private fun loadAllFavoriteTracks(): Flow<MainResult> {
        return flow {
            trackRepository.loadFavoriteTracks()
        }
    }

    private fun subscribeToTracks(): Flow<MainResult> {
        return trackRepository.getFavoriteTracksFlow()
            .map { MainResult.FavoriteTracksLoaded(it) }
    }

    private fun navigateToSelectedTab(tab: HomeSections): Flow<MainResult> {
        return flow {
            emit(MainResult.NavigateToSelectedTab(tab))
        }
    }

    override fun actionProcessor(actions: Flow<MainAction>): Flow<MainResult> {
        return merge(
            actions.filterIsInstance<MainAction.LoadAllTabs>().flatMapLatest { loadAllTabs() },
            actions.filterIsInstance<MainAction.LoadAllFavoriteTracks>().flatMapLatest { loadAllFavoriteTracks() },
            actions.filterIsInstance<MainAction.SubscribeToFavoriteTrack>().flatMapLatest { subscribeToTracks() },
            actions.filterIsInstance<MainAction.SelectedTab>().flatMapLatest { navigateToSelectedTab(it.tab) }
        )
    }
}
