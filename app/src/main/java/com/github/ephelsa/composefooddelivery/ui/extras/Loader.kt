package com.github.ephelsa.composefooddelivery.ui.extras

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@ExperimentalAnimationApi
@Composable
fun Loader(
    isLoading: Boolean,
    modifier: Modifier = Modifier,
    loader: @Composable AnimatedVisibilityScope.() -> Unit = { CircularProgressIndicator() },
    content: @Composable AnimatedVisibilityScope.() -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(
            visible = isLoading,
            content = loader
        )

        AnimatedVisibility(
            visible = !isLoading,
            content = content
        )
    }
}