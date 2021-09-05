package com.github.ephelsa.composefooddelivery.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.github.ephelsa.composefooddelivery.R
import com.github.ephelsa.ui.button.SimpleIconButton
import com.github.ephelsa.ui.button.ThumbImageIcon
import com.github.ephelsa.ui.toolbar.ApplicationToolbar

@Composable
fun UserFoodDeliveryToolbar(
    onProfileClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    ApplicationToolbar(
        leftItem = {
            ThumbImageIcon(
                imageVector = Icons.Filled.Person,
                contentDescription = "Profile",
                onClick = onProfileClick
            )
        },
        rightItem = {
            SimpleIconButton(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_grid_layout),
                contentDescription = "Settings",
                fillBackground = false,
                onClick = onSettingsClick
            )
        }
    )
}