@file:OptIn(
    ExperimentalSharedTransitionApi::class
)

package com.fcerio.trackit.features.main.composable

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.navArgument
import com.fcerio.trackit.components.navgraph.composableWithCompositionLocal
import com.fcerio.trackit.navigation.HomeSections
import com.fcerio.trackit.navigation.MainDestinations
import com.fcerio.trackit.navigation.rememberAppNavController

@OptIn(ExperimentalSharedTransitionApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreenComposable() {
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
                    MainContainerComposable(navController = navController)
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
//                    TrackDetailScreen(
//                        trackId = { trackId },
//                        upPress = navController::upPress
//                    )
                }
            }
        }
    }
}

val LocalNavAnimatedVisibilityScope = compositionLocalOf<AnimatedVisibilityScope?> { null }
val LocalSharedTransitionScope = compositionLocalOf<SharedTransitionScope?> { null }
