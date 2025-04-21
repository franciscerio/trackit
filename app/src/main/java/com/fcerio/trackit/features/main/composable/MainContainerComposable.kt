package com.fcerio.trackit.features.main.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import com.fcerio.trackit.components.AppScaffold
import com.fcerio.trackit.components.rememberAppScaffoldState
import com.fcerio.trackit.features.main.MainViewModel
import com.fcerio.trackit.navigation.AppNavController
import com.fcerio.trackit.navigation.HomeSections
import com.fcerio.trackit.navigation.rememberAppNavController

@Composable
fun MainContainer(navController: AppNavController) {
    val appScaffoldState = rememberAppScaffoldState()
    val nestedNavController = rememberAppNavController()
    val navBackStackEntry by nestedNavController.navController.currentBackStackEntryAsState()
    val currentRoute by remember {
        derivedStateOf {
            navBackStackEntry?.destination?.route ?: HomeSections.HOME.route
        }
    }
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
                    MainNavigationBar(
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

val LocalNavAnimatedVisibilityScope = compositionLocalOf<AnimatedVisibilityScope?> { null }
val LocalSharedTransitionScope = compositionLocalOf<SharedTransitionScope?> { null }
