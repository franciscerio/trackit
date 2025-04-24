package com.fcerio.trackit.features.main.composable

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.fcerio.core.common.compose.utils.nonSpatialExpressiveSpring
import com.fcerio.core.common.compose.utils.spatialExpressiveSpring
import com.fcerio.trackit.components.AppScaffold
import com.fcerio.trackit.components.AppSnackBar
import com.fcerio.trackit.components.navgraph.addHomeGraph
import com.fcerio.trackit.components.rememberAppScaffoldState
import com.fcerio.trackit.features.main.MainViewModel
import com.fcerio.trackit.navigation.AppNavController
import com.fcerio.trackit.navigation.HomeSections
import com.fcerio.trackit.navigation.rememberAppNavController

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MainContainerComposable(navController: AppNavController) {
    val appScaffoldState = rememberAppScaffoldState()
    val nestedNavController = rememberAppNavController()
    val navBackStackEntry by nestedNavController.navController.currentBackStackEntryAsState()
//    val currentRoute by remember {
//        derivedStateOf {
//            navBackStackEntry?.destination?.route ?: HomeSections.HOME.route
//        }
//    }
    val sharedTransitionScope = LocalSharedTransitionScope.current
        ?: throw IllegalStateException("No SharedElementScope found")
    val animatedVisibilityScope = LocalNavAnimatedVisibilityScope.current
        ?: throw IllegalStateException("No SharedElementScope found")

    val viewModel = hiltViewModel<MainViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.navigateToSelectedTab) {
        uiState.navigateToSelectedTab.get(uiState)?.let {
            nestedNavController.navigateToBottomBarRoute(it.route)
        }
    }

    AppScaffold(
        bottomBar = {
            with(animatedVisibilityScope) {
                with(sharedTransitionScope) {
                    MainNavigationBarComposable(
                        tabs = uiState.tabs,
                        currentRoute = { uiState.selectedTab },
                        favoriteTracksCount = { uiState.favoriteBadgeCount },
                        navigateToRoute = {
                            viewModel.selectedTab(it)
                        },
                        modifier = Modifier
                            .renderInSharedTransitionScopeOverlay(
                                zIndexInOverlay = 1f,
                            )
                            .animateEnterExit(
                                enter = fadeIn(nonSpatialExpressiveSpring()) + slideInVertically(
                                    spatialExpressiveSpring()
                                ) {
                                    it
                                },
                                exit = fadeOut(nonSpatialExpressiveSpring()) + slideOutVertically(
                                    spatialExpressiveSpring()
                                ) {
                                    it
                                }
                            )
                    )
                }
            }
        },
        modifier = Modifier.fillMaxSize(),
        snackbarHost = {
            SnackbarHost(
                hostState = it,
                modifier = Modifier.systemBarsPadding(),
                snackbar = { snackbarData -> AppSnackBar(snackbarData) }
            )
        },
        snackBarHostState = appScaffoldState.snackBarHostState,
    ) { padding ->
        NavHost(
            navController = nestedNavController.navController,
            startDestination = HomeSections.HOME.route
        ) {
            addHomeGraph(
                modifier = Modifier
                    .padding(padding)
                    .consumeWindowInsets(padding),
                navController = { navController }
            )
        }
    }
}