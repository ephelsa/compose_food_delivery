package com.github.ephelsa.ui.card

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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
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
            .drawBehind {
                val yOffset = size.height - (IconBoxSize.value * 1.3f)
                val yEnd = yOffset - 96f
                val ovalWidth = 80f
                val ovalSize = Size(ovalWidth, 90f)

                val innerPath = Path().apply {
                    val innerYOffset = yOffset - 50f

                    moveTo(0f, innerYOffset)
                    arcTo(Rect(Offset(size.width / 2, yOffset), IconBoxSize.value * 1.9f), -160f, 140f, false)
                    lineTo(size.width, innerYOffset)
                    lineTo(size.width, size.height)
                    lineTo(0f, size.height)
                    close()
                }

                drawPath(innerPath, surfaceColor)
                drawOval(boxColor, Offset(0f, yEnd), ovalSize)
                drawOval(boxColor, Offset(size.width - ovalWidth, yEnd), ovalSize)
            }
            .clickable(onClick = onClick)
    ) {
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