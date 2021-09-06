package com.github.ephelsa.composefooddelivery.ui.extras

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.github.ephelsa.composefooddelivery.ComposeFoodDeliveryViewModel
import com.github.ephelsa.composefooddelivery.R
import com.github.ephelsa.ui.button.SimpleIconButton
import com.github.ephelsa.ui.button.ThumbImageIcon
import com.github.ephelsa.ui.toolbar.ApplicationToolbar

@ExperimentalAnimationApi
@ExperimentalCoilApi
@Composable
fun UserFoodDeliveryToolbar(
    viewModel: ComposeFoodDeliveryViewModel = viewModel(),
    onProfileClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    val thumbURL by viewModel.onUserThumbnail.collectAsState()
    val painter = rememberImagePainter(
        data = thumbURL
    )
    val shouldLoad by viewModel.onLoadUserThumbnail.collectAsState()

    ApplicationToolbar(
        leftItem = {
            UserThumbnail(!shouldLoad, painter, onProfileClick)
        },
        rightItem = {
            SimpleIconButton(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_grid_layout),
                contentDescription = "Settings",
                fillBackground = false,
                paddingIcon = 16.dp,
                onClick = onSettingsClick
            )
        }
    )
}

@ExperimentalAnimationApi
@Composable
fun UserThumbnail(
    isVisible: Boolean,
    painter: Painter,
    onProfileClick: () -> Unit
) {
    Box {
        AnimatedVisibility(
            visible = !isVisible
        ) {
            CircularProgressIndicator()
        }

        AnimatedVisibility(
            visible = isVisible,
        ) {
            ThumbImageIcon(
                painter = painter,
                contentDescription = "Profile",
                onClick = onProfileClick
            )
        }
    }
}