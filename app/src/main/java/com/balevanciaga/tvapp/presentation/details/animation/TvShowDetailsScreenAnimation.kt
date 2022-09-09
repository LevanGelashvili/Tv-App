package com.balevanciaga.tvapp.presentation.details.animation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.navigation.NavBackStackEntry
import com.ramcosta.composedestinations.spec.DestinationStyle

@OptIn(ExperimentalAnimationApi::class)
object TvShowDetailsScreenAnimation : DestinationStyle.Animated {

    private val tweenSpec = tween<Float>(
        durationMillis = 500,
        easing = CubicBezierEasing(0.08f, 0.93f, 0.68f, 1.27f)
    )

    override fun AnimatedContentScope<NavBackStackEntry>.popEnterTransition(): EnterTransition {
        return fadeIn(animationSpec = tweenSpec)
    }
}