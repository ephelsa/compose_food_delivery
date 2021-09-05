package com.github.ephelsa.composefooddelivery.ui.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.github.ephelsa.ui.button.SimpleIconButton
import com.github.ephelsa.ui.card.CatalogImageCard
import com.github.ephelsa.ui.card.IngredientCard
import com.github.ephelsa.ui.theme.ExtraHugeSpacing
import com.github.ephelsa.ui.theme.HugeSpacing
import com.github.ephelsa.ui.theme.LargeSpacing
import com.github.ephelsa.ui.toolbar.ApplicationToolbar

@Composable
fun DetailsScreen() {
    val (isFavorite, setFavorite) = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            DetailsToolbar(
                isFavorite = isFavorite,
                onBackClick = {},
                onFavoriteClick = {
                    setFavorite(!isFavorite)
                }
            )
        },
        bottomBar = {
            Footer()
        }
    ) {
        DetailsBody()
    }
}

@Composable
private fun DetailsToolbar(
    isFavorite: Boolean,
    onBackClick: () -> Unit,
    onFavoriteClick: () -> Unit,
) {
    ApplicationToolbar(
        leftItem = {
            SimpleIconButton(
                imageVector = Icons.Rounded.KeyboardArrowLeft,
                contentDescription = "Settings",
                fillBackground = false,
                onClick = onBackClick
            )
        },
        rightItem = {
            SimpleIconButton(
                imageVector = if (isFavorite) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
                contentDescription = "Settings",
                fillBackground = false,
                onClick = onFavoriteClick,
                iconColor = if (isFavorite) MaterialTheme.colors.primary else MaterialTheme.colors.onBackground
            )
        }
    )
}

@Composable
private fun DetailsBody() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = HugeSpacing)
            .verticalScroll(rememberScrollState())
    ) {
        CatalogImageCard(Icons.Rounded.Lock, null)
        Spacer(Modifier.height(ExtraHugeSpacing))
        InfoSection()
        Spacer(Modifier.height(ExtraHugeSpacing))
        IngredientsSection()
    }
}

@Composable
private fun InfoSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Hamburger",
                style = MaterialTheme.typography.h5
            )

            Text(
                text = "Delivery in 20Min",
                style = MaterialTheme.typography.body2,
            )

            Text(
                text = "This is our best burger",
                style = MaterialTheme.typography.body1
            )
        }

        Text(
            text = "$19.99",
            style = MaterialTheme.typography.h3,
            color = MaterialTheme.colors.primary,
            fontWeight = FontWeight.Normal
        )
    }
}

@Composable
private fun IngredientsSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Text(
            text = "Ingredients",
            style = MaterialTheme.typography.h5
        )

        Spacer(Modifier.height(ExtraHugeSpacing))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(LargeSpacing)
        ) {
            items(10) {
                IngredientCard(
                    imageVector = Icons.Filled.Phone,
                    text = "Phone",
                    backgroundColor = MaterialTheme.colors.secondary
                ) {

                }
            }
        }
    }
}

@Composable
private fun Footer() {
    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .fillMaxWidth()
            .padding(HugeSpacing)
            .height(55.dp),
        shape = CircleShape
    ) {
        Text(text = "Add to Cart")
    }
}