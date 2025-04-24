@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.fcerio.core.common.compose.utils

import androidx.compose.animation.BoundsTransform
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.core.spring

fun <T> spatialExpressiveSpring() = spring<T>(
    dampingRatio = 0.8f,
    stiffness = 380f
)

fun <T> nonSpatialExpressiveSpring() = spring<T>(
    dampingRatio = 1f,
    stiffness = 1600f
)

val snackDetailBoundsTransform = BoundsTransform { _, _ ->
    spatialExpressiveSpring()
}
