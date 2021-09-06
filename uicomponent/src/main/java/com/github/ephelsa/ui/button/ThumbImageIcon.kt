package com.github.ephelsa.ui.button

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ThumbImageIcon(
    imageVector: ImageVector,
    contentDescription: String?,
    onClick: () -> Unit
) = ThumbImageIcon(rememberVectorPainter(imageVector), contentDescription, onClick)

@Composable
fun ThumbImageIcon(
    painter: Painter,
    contentDescription: String?,
    onClick: () -> Unit,
) {
    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = Modifier
            .clip(CircleShape)
            .size(40.dp)
            .border(
                width = 2.dp,
                color = MaterialTheme.colors.primary,
                shape = CircleShape
            )
            .background(
                color = MaterialTheme.colors.surface,
            )
            .clickable(
                onClick = onClick
            )
    )
}

@Preview
@Composable
internal fun ThumbImageIconPreview() {
    ThumbImageIcon(
        imageVector = Icons.Filled.Person,
        contentDescription = "Profile"
    ) {

    }
}

