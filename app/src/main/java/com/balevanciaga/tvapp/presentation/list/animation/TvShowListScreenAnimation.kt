package com.balevanciaga.tvapp.presentation.list.animation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.ui.unit.IntOffset
import androidx.navigation.NavBackStackEntry
import com.ramcosta.composedestinations.spec.DestinationStyle

@OptIn(ExperimentalAnimationApi::class)
object TvShowListScreenAnimation : DestinationStyle.Animated {

    private const val offsetX = -1200
    private val tweenSpec = tween<IntOffset>(
        durationMillis = 500,
        easing = CubicBezierEasing(0.08f, 0.93f, 0.68f, 1.27f)
    )

    override fun AnimatedContentScope<NavBackStackEntry>.enterTransition(): EnterTransition {
        return slideInHorizontally(
            animationSpec = tweenSpec,
            initialOffsetX = { offsetX }
        )
    }

    override fun AnimatedContentScope<NavBackStackEntry>.popEnterTransition(): EnterTransition {
        return slideInHorizontally(
            animationSpec = tweenSpec,
            initialOffsetX = { offsetX }
        )
    }
}