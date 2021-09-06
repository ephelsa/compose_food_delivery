package com.github.ephelsa.composefooddelivery.ui.extras

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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

@ExperimentalCoilApi
@Composable
fun UserFoodDeliveryToolbar(
    viewModel: ComposeFoodDeliveryViewModel = viewModel(),
    onProfileClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    val thumbURL by viewModel.onUserThumbnail.collectAsState()
    val shouldLoad by viewModel.onLoadUserThumbnail.collectAsState()

    ApplicationToolbar(
        leftItem = {
            if (shouldLoad) {
                CircularProgressIndicator()
            } else {
                ThumbImageIcon(
                    painter = rememberImagePainter(
                        data = thumbURL
                    ),
                    contentDescription = "Profile",
                    onClick = onProfileClick
                )
            }
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