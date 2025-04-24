package com.fcerio.trackit.components.navgraph

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.fcerio.core.common.compose.utils.nonSpatialExpressiveSpring
import com.fcerio.trackit.features.explore.composable.ExploreScreenComposable
import com.fcerio.trackit.features.main.composable.LocalNavAnimatedVisibilityScope
import com.fcerio.trackit.navigation.AppNavController
import com.fcerio.trackit.navigation.HomeSections

fun NavGraphBuilder.addHomeGraph(
    modifier: Modifier = Modifier,
    navController: () -> AppNavController
) {
    composable(HomeSections.HOME.route) { from ->
        ExploreScreenComposable(
            onTrackClickListener = {
                navController().navigateToTrackDetail(it, from)
            },
            onTrackFavoriteClickListener = {
            },
            modifier
        )
    }
    composable(HomeSections.FAVORITES.route) { from ->
//        FavoritesScreen(
//            onTrackClickListener = {
//                navController().navigateToTrackDetail(it, from)
//            },
//            onTrackFavoriteClickListener = {
//            },
//            modifier
//        )
    }
    composable(HomeSections.PROFILE.route) { from ->
//        ProfileScreen(modifier)
    }
}

fun NavGraphBuilder.composableWithCompositionLocal(
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    enterTransition: (
    @JvmSuppressWildcards
    AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?
    )? = {
        fadeIn(nonSpatialExpressiveSpring())
    },
    exitTransition: (
    @JvmSuppressWildcards
    AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?
    )? = {
        fadeOut(nonSpatialExpressiveSpring())
    },
    popEnterTransition: (
    @JvmSuppressWildcards
    AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?
    )? =
        enterTransition,
    popExitTransition: (
    @JvmSuppressWildcards
    AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?
    )? =
        exitTransition,
    content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {
    composable(
        route,
        arguments,
        deepLinks,
        enterTransition,
        exitTransition,
        popEnterTransition,
        popExitTransition
    ) {
        CompositionLocalProvider(
            LocalNavAnimatedVisibilityScope provides this@composable
        ) {
            content(it)
        }
    }
}