package com.github.ephelsa.ui.card

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.ephelsa.ui.button.SimpleIconButton
import com.github.ephelsa.ui.theme.AquaHaze
import com.github.ephelsa.ui.theme.MediumSpacing1
import com.github.ephelsa.ui.theme.PinkSwan
import com.github.ephelsa.ui.theme.SmallSpacing

private val CardWidth = 140.dp
private val CardHeight = 220.dp

@Composable
fun RecommendedCard(
    imageVector: ImageVector,
    name: String,
    label: String,
    price: Double,
    isAvailable: Boolean = true,
    onDetails: () -> Unit,
    onAdd: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(CardWidth, CardHeight)
            .clip(MaterialTheme.shapes.medium)
            .clickable(onClick = onDetails)
    ) {
        InformationCard(name, label, price, isAvailable, onAdd)
        Image(
            imageVector = imageVector,
            contentDescription = "$name $price",
            modifier = Modifier
                .height((CardHeight.value * 0.6).dp)
                .fillMaxWidth()
        )
    }
}

@Composable
private fun InformationCard(
    name: String,
    label: String,
    price: Double,
    isAvailable: Boolean,
    onAdd: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = (CardHeight.value * 0.4).dp)
            .clip(MaterialTheme.shapes.medium)
            .background(AquaHaze)
            .padding(horizontal = MediumSpacing1)
            .padding(
                top = (CardHeight.value * 0.2).dp,
                bottom = MediumSpacing1
            ),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.body2,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(SmallSpacing))

        Text(
            text = label,
            style = MaterialTheme.typography.body1,
            color = PinkSwan,
            fontSize = 12.sp
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "$$price",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.primary
            )

            SimpleIconButton(
                imageVector = Icons.Rounded.Add,
                contentDescription = null,
                fillBackground = true,
                size = 20.dp,
                paddingIcon = 2.dp,
                enabled = isAvailable,
                backgroundColor = MaterialTheme.colors.secondary,
                onClick = onAdd
            )
        }
    }
}

@Preview
@Composable
internal fun RecommendedCardPreview() {
    val context = LocalContext.current

    RecommendedCard(
        imageVector = Icons.Filled.Share,
        name = "Sandwich",
        label = "Starting From",
        price = 15.50,
        isAvailable = false,
        onDetails = {},
        onAdd = {
            Toast.makeText(context, "Add", Toast.LENGTH_SHORT).show()
        }
    )
}