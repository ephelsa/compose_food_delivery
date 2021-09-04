package com.github.ephelsa.ui.button

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.github.ephelsa.ui.R
import com.github.ephelsa.ui.theme.PinkSwan

@Composable
fun SimpleIconButton(
    imageVector: ImageVector,
    contentDescription: String?,
    fillBackground: Boolean,
    size: Dp = 50.dp,
    enabled: Boolean = true,
    backgroundColor: Color = MaterialTheme.colors.primary,
    iconColor: Color = PinkSwan,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(size)
            .background(
                color = if (fillBackground) backgroundColor else Color.Transparent,
                shape = MaterialTheme.shapes.small
            )
            .clickable(
                enabled = enabled,
                onClick = onClick
            )
            .padding(12.dp)
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
        imageVector = ImageVector.vectorResource(id = R.drawable.ic_tune),
        contentDescription = "Settings",
        fillBackground = true,
    ) {

    }
}
