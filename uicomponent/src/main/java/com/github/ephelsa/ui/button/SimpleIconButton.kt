package com.github.ephelsa.ui.button

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.github.ephelsa.ui.theme.PinkSwan

@Composable
fun SimpleIconButton(
    imageVector: ImageVector,
    contentDescription: String?,
    fillBackground: Boolean,
    size: Dp = 50.dp,
    paddingIcon: Dp = 12.dp,
    enabled: Boolean = true,
    backgroundColor: Color = MaterialTheme.colors.primary,
    iconColor: Color = MaterialTheme.colors.onBackground,
    onClick: () -> Unit
) {
    val backgroundFill = if (fillBackground) {
        if (enabled) backgroundColor else PinkSwan
    } else {
        Color.Transparent
    }

    Box(
        modifier = Modifier
            .clip(MaterialTheme.shapes.small)
            .size(size)
            .background(
                color = backgroundFill,
            )
            .clickable(
                enabled = enabled,
                onClick = onClick,
                role = Role.Button
            )
            .padding(paddingIcon)
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            modifier = Modifier
                .fillMaxSize(),
            tint = if (fillBackground) MaterialTheme.colors.surface else iconColor
        )
    }
}

@Preview
@Composable
internal fun SimpleIconButtonPreview() {
    SimpleIconButton(
        imageVector = Icons.Rounded.Settings,
        contentDescription = "Settings",
        fillBackground = true,
    ) {

    }
}
