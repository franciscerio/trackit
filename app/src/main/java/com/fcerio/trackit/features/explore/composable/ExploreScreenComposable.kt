package com.fcerio.trackit.features.explore.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.fcerio.core.common.compose.AppTheme
import com.fcerio.core.common.compose.components.SearchBarComposable
import com.fcerio.core.common.compose.components.TitleBarComposable
import com.fcerio.core.common.compose.dimensions.Margins
import com.fcerio.features.tracks.ui.SongCard
import com.fcerio.trackit.R
import com.fcerio.trackit.components.Loading
import com.fcerio.trackit.features.explore.ExploreViewModel

private typealias OnTrackClickListener = (Long) -> Unit
private typealias OnTrackFavoriteClickListener = (Long) -> Unit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExploreScreenComposable(
    onTrackClickListener: OnTrackClickListener = {},
    onTrackFavoriteClickListener: OnTrackFavoriteClickListener = {},
    modifier: Modifier
) {
    val viewModel = hiltViewModel<ExploreViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val state = rememberPullToRefreshState()
    val lazyPagingItems = viewModel.pager.collectAsLazyPagingItems()

    Surface(modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(AppTheme.colors.neutralsBackground)
        ) {
            // Modifier the title bar, TopAppBar to it will check the StatusBar height
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = AppTheme.colors.neutralsBackground,
                    titleContentColor = AppTheme.colors.primaryDefault,
                ),
                title = {
                    TitleBarComposable(
                        Modifier.wrapContentWidth(),
                        title = stringResource(R.string.explore)
                    )
                }
            )

            SearchBarComposable(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Margins.LARGE),
                value = "",
                onValueChanged = {

                }
            )

            PullToRefreshBox(
                isRefreshing = uiState.isRefreshing,
                onRefresh = {
                    viewModel.pulledToRefresh()
                },
                state = state,
                indicator = {
                    Indicator(
                        modifier = Modifier.align(Alignment.TopCenter),
                        isRefreshing = uiState.isRefreshing,
                        containerColor = AppTheme.colors.primaryShade2,
                        color = AppTheme.colors.primaryDefault,
                        state = state
                    )
                },
                modifier = Modifier.fillMaxSize()
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(
                        start = Margins.LARGE,
                        end = Margins.LARGE,
                        top = Margins.SMALL,
                        bottom = Margins.SMALL
                    ),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 1.dp),
                    content = {
                        items(
                            lazyPagingItems.itemCount,
                            lazyPagingItems.itemKey {
                                it.id
                            }
                        ) { index ->
                            val track = lazyPagingItems[index]
                            if (track != null) {
                                SongCard(
                                    model = { track },
                                    onClicked = {
                                        onTrackClickListener.invoke(it.id)
                                    },
                                    onFavoriteClicked = {
                                        onTrackFavoriteClickListener.invoke(it.id)
                                    }
                                )
                            } else {
                                Loading()
                            }
                        }
                    }
                )
            }
        }
    }
}
