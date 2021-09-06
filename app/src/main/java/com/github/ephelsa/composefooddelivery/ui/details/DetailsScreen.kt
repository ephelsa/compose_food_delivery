package com.github.ephelsa.composefooddelivery.ui.details

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.github.ephelsa.composefooddelivery.R
import com.github.ephelsa.composefooddelivery.ui.extras.Loader
import com.github.ephelsa.domain.Product
import com.github.ephelsa.ui.button.SimpleIconButton
import com.github.ephelsa.ui.card.CatalogImageCard
import com.github.ephelsa.ui.card.IngredientCard
import com.github.ephelsa.ui.theme.ExtraHugeSpacing
import com.github.ephelsa.ui.theme.HugeSpacing
import com.github.ephelsa.ui.theme.LargeSpacing
import com.github.ephelsa.ui.toolbar.ApplicationToolbar

@ExperimentalAnimationApi
@ExperimentalCoilApi
@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel,
    onBackClick: () -> Unit
) {
    val (isFavorite, setFavorite) = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            DetailsToolbar(
                isFavorite = isFavorite,
                onBackClick = onBackClick,
                onFavoriteClick = {
                    setFavorite(!isFavorite)
                }
            )
        },
        bottomBar = {
            Footer()
        }
    ) {
        DetailsBody(viewModel)
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
                contentDescription = stringResource(R.string.contentDescription_backPage),
                fillBackground = false,
                onClick = onBackClick
            )
        },
        rightItem = {
            SimpleIconButton(
                imageVector = if (isFavorite) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
                contentDescription = stringResource(
                    id = if (isFavorite) R.string.contentDescription_unsetFavorite else R.string.contentDescription_setFavorite
                ),
                fillBackground = false,
                onClick = onFavoriteClick,
                iconColor = if (isFavorite) MaterialTheme.colors.primary else MaterialTheme.colors.onBackground
            )
        }
    )
}

@ExperimentalAnimationApi
@ExperimentalCoilApi
@Composable
private fun DetailsBody(
    viewModel: DetailsViewModel,
) {
    val shouldLoad by viewModel.onLoadingDetails.collectAsState()
    val details by viewModel.onDetails.collectAsState()

    Loader(
        isLoading = shouldLoad,
        modifier = Modifier.fillMaxWidth()
    ) {
        if (details == null) {
            Text(
                text = "Information not found",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.error
            )
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = HugeSpacing)
                    .verticalScroll(rememberScrollState())
            ) {
                CatalogImageCard(
                    painter = rememberImagePainter(data = details!!.image),
                    contentDescription = null
                )
                Spacer(Modifier.height(ExtraHugeSpacing))
                InfoSection(details!!)
                Spacer(Modifier.height(ExtraHugeSpacing))
                IngredientsSection(details!!)
            }
        }
    }
}

@Composable
private fun InfoSection(
    details: Product
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = details.name,
                style = MaterialTheme.typography.h5
            )

            Text(
                text = stringResource(R.string.details_deliveryIn, details.expectedDelivery / 1_000_000),
                style = MaterialTheme.typography.body2,
            )

            Text(
                text = details.description ?: stringResource(R.string.empty_description),
                style = MaterialTheme.typography.body1
            )
        }

        Text(
            text = "$${details.price}",
            style = MaterialTheme.typography.h3,
            color = MaterialTheme.colors.primary,
            fontWeight = FontWeight.Normal
        )
    }
}

@ExperimentalCoilApi
@Composable
private fun IngredientsSection(
    details: Product
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Text(
            text = stringResource(R.string.sectionLabel_ingredients),
            style = MaterialTheme.typography.h5
        )

        Spacer(Modifier.height(ExtraHugeSpacing))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(LargeSpacing)
        ) {
            items(details.ingredients) {
                IngredientCard(
                    painter = rememberImagePainter(
                        data = it.image
                    ),
                    text = it.name,
                    backgroundColor = MaterialTheme.colors.secondary
                )
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
        Text(text = stringResource(R.string.buttonLabel_addToCart))
    }
}