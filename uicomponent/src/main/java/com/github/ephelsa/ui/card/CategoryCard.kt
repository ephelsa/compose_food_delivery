package com.github.ephelsa.ui.card

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.ephelsa.ui.theme.AquaHaze
import com.github.ephelsa.ui.theme.MediumSpacing1

private val CardHeight = 140.dp
private val CardWidth = 80.dp
private val IconBoxSize = 28.dp

@Composable
fun CategoryCard(
    text: String,
    imageVector: ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit
) = CategoryCard(text, rememberVectorPainter(imageVector), isSelected, onClick)

@Composable
fun CategoryCard(
    text: String,
    painter: Painter,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val surfaceColor = MaterialTheme.colors.surface
    val boxColor = if (isSelected) MaterialTheme.colors.primary else AquaHaze

    Box(
        modifier = Modifier
            .size(CardWidth, CardHeight)
            .clip(MaterialTheme.shapes.large)
            .background(boxColor)
            .clickable(onClick = onClick)
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // Center oval
            val circleRadius = IconBoxSize.value * 1.4f
            drawCircle(
                color = surfaceColor,
                radius = IconBoxSize.value * 1.6f,
                center = Offset(size.width / 2f, size.height - circleRadius)
            )

            // Rectangle cut
            val rectangleHeight = size.height / 10f
            drawRect(
                color = surfaceColor,
                topLeft = Offset(0f, size.height - rectangleHeight),
                size = Size(size.width, rectangleHeight)
            )
        }
        CategoryCardContent(text, painter, isSelected)
    }
}

@Composable
private fun CategoryCardContent(
    text: String,
    painter: Painter,
    isSelected: Boolean,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = MediumSpacing1)
            .padding(top = MediumSpacing1),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painter,
            contentDescription = text,
            modifier = Modifier
                .fillMaxWidth()
                .height((CardHeight.value * 0.4).dp),
        )

        Text(
            text = text,
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Bold,
            color = if (isSelected) MaterialTheme.colors.surface else MaterialTheme.colors.onSecondary,
            fontSize = 12.sp
        )

        CategoryIcon(isSelected)
    }
}

@Composable
private fun CategoryIcon(
    isSelected: Boolean
) {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(
                color = if (isSelected) MaterialTheme.colors.secondary else MaterialTheme.colors.onSecondary
            )
            .size(IconBoxSize),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Rounded.KeyboardArrowRight,
            contentDescription = null,
            modifier = Modifier
                .size(18.dp),
            tint = if (isSelected) MaterialTheme.colors.onSecondary else MaterialTheme.colors.surface
        )
    }
}

@Preview
@Composable
internal fun CategoryCardPreview() {
    Column {
        CategoryCard(
            text = "Sandwich",
            imageVector = Icons.Filled.Email,
            isSelected = false
        ) {

        }

        CategoryCard(
            text = "Sandwich",
            imageVector = Icons.Filled.Email,
            isSelected = true
        ) {

        }
    }
}