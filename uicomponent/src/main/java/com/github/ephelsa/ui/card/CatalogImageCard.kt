package com.github.ephelsa.ui.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import com.github.ephelsa.ui.theme.AquaHaze

@Composable
fun CatalogImageCard(
    image: ImageVector,
    contentDescription: String?,
    backgroundColor: Color = AquaHaze
) = CatalogImageCard(rememberVectorPainter(image), contentDescription, backgroundColor)

@Composable
fun CatalogImageCard(
    painter: Painter,
    contentDescription: String?,
    backgroundColor: Color = AquaHaze
) {
    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = Modifier
            .fillMaxWidth()
            .clip(CircleShape)
            .background(backgroundColor),
        contentScale = ContentScale.FillWidth
    )
}

@Preview
@Composable
internal fun CatalogImageCardPreview() {
    CatalogImageCard(Icons.Rounded.Lock, null)
}

