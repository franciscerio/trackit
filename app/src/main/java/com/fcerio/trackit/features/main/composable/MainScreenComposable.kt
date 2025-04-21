@file:OptIn(
    ExperimentalSharedTransitionApi::class
)

package com.fcerio.trackit.features.main.composable

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
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
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.fcerio.trackit.components.AppScaffold
import com.fcerio.trackit.components.rememberAppScaffoldState
import com.fcerio.trackit.features.main.MainViewModel
import com.fcerio.trackit.navigation.AppNavController
import com.fcerio.trackit.navigation.HomeSections
import com.fcerio.trackit.navigation.rememberAppNavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberAppNavController()
    SharedTransitionLayout {
        CompositionLocalProvider(
            LocalSharedTransitionScope provides this
        ) {
            NavHost(
                navController = navController.navController,
                startDestination = HomeSections.HOME.route
            ) {
                composableWithCompositionLocal(
                    route = HomeSections.HOME.route
                ) { _ ->
                    MainContainer(navController = navController)
                }

                composableWithCompositionLocal(
                    "${MainDestinations.TRACK_DETAIL_ROUTE}/" +
                        "{${MainDestinations.TRACK_ID_KEY}}",
                    arguments = listOf(
                        navArgument(MainDestinations.TRACK_ID_KEY) {
                            type = NavType.LongType
                        }
                    )
                ) { backStackEntry ->
                    val arguments = requireNotNull(backStackEntry.arguments)
                    val trackId = arguments.getLong(MainDestinations.TRACK_ID_KEY)
                    TrackDetailScreen(
                        trackId = { trackId },
                        upPress = navController::upPress
                    )
                }
            }
        }
    }
}

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
