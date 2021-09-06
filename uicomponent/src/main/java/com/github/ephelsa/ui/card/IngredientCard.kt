package com.github.ephelsa.ui.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.ephelsa.ui.theme.MediumSpacing
import com.github.ephelsa.ui.theme.MediumSpacing1

private val CategoryCardHeight = 90.dp
private val CategoryCardWidth = 70.dp

@Composable
fun IngredientCard(
    imageVector: ImageVector,
    text: String,
    backgroundColor: Color,
) = IngredientCard(rememberVectorPainter(imageVector), text, backgroundColor)

@Composable
fun IngredientCard(
    painter: Painter,
    text: String,
    backgroundColor: Color,
) {
    Box(
        modifier = Modifier
            .size(CategoryCardWidth, CategoryCardHeight)
            .clip(MaterialTheme.shapes.medium)
            .background(backgroundColor)
            .padding(
                horizontal = MediumSpacing1,
                vertical = MediumSpacing
            )
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painter,
                contentDescription = text,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
            )

            Text(
                text = text,
                style = MaterialTheme.typography.body1,
                fontSize = 12.sp,
                maxLines = 1
            )
        }
    }
}

@Preview
@Composable
internal fun IngredientCardPreview() {
    IngredientCard(
        imageVector = Icons.Filled.Phone,
        text = "Phone",
        backgroundColor = Color.Yellow
    )
}