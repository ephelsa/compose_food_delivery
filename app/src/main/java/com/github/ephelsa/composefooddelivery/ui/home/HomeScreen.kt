package com.github.ephelsa.composefooddelivery.ui.home

import android.widget.Toast
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.github.ephelsa.composefooddelivery.R
import com.github.ephelsa.composefooddelivery.route.ComposeFoodDeliveryScreen
import com.github.ephelsa.composefooddelivery.ui.extras.Loader
import com.github.ephelsa.domain.Category
import com.github.ephelsa.ui.button.SimpleIconButton
import com.github.ephelsa.ui.card.CategoryCard
import com.github.ephelsa.ui.card.RecommendedCard
import com.github.ephelsa.ui.theme.ExtraHugeSpacing
import com.github.ephelsa.ui.theme.HugeSpacing
import com.github.ephelsa.ui.theme.LargeSpacing

@ExperimentalAnimationApi
@ExperimentalCoilApi
@Composable
fun HomeBody(
    homeViewModel: HomeViewModel,
    screen: (ComposeFoodDeliveryScreen) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = HugeSpacing
            )
            .verticalScroll(rememberScrollState())
    ) {
        Title()
        Spacer(Modifier.height(ExtraHugeSpacing))
        Search()
        Spacer(Modifier.height(ExtraHugeSpacing))
        CategorySection(homeViewModel, Category.CategoryType.Burger) {}
        Spacer(Modifier.height(ExtraHugeSpacing))
        RecommendedSection(screen)
    }
}

@Composable
private fun Title() {
    val appName = stringResource(id = R.string.app_name)
        .split(" ")

    Column {
        appName.mapIndexed { i, v ->
            Text(
                text = v,
                style = MaterialTheme.typography.h4,
                fontWeight = if (i != appName.size - 1) FontWeight.Normal else null
            )
        }
    }
}

@Composable
private fun Search() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // TODO: Search field
        SimpleIconButton(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_tune),
            contentDescription = "Filter",
            fillBackground = true
        ) {

        }
    }
}

@ExperimentalAnimationApi
@ExperimentalCoilApi
@Composable
private fun CategorySection(
    homeViewModel: HomeViewModel,
    categorySelected: Category.CategoryType,
    onClick: (Category.CategoryType) -> Unit
) {
    val shouldLoad by homeViewModel.onLoadingCategories.collectAsState()
    val categories by homeViewModel.onCategories.collectAsState()

    Column {
        Text(
            text = stringResource(R.string.sectionLabel_categories),
            style = MaterialTheme.typography.h5
        )

        Spacer(Modifier.height(ExtraHugeSpacing))

        Loader(
            isLoading = shouldLoad,
            modifier = Modifier.fillMaxWidth()
        ) {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(LargeSpacing)
            ) {
                items(categories) {
                    CategoryCard(
                        text = it.name,
                        painter = rememberImagePainter(
                            data = it.image
                        ),
                        isSelected = categorySelected == it.categoryType,
                        onClick = { onClick(it.categoryType) }
                    )
                }
            }
        }
    }
}

@Composable
private fun RecommendedSection(
    screen: (ComposeFoodDeliveryScreen) -> Unit
) {
    val context = LocalContext.current

    Column {
        Text(
            text = stringResource(R.string.sectionLabel_recommended),
            style = MaterialTheme.typography.h6
        )

        Spacer(Modifier.height(ExtraHugeSpacing))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(LargeSpacing)
        ) {
            items(10) {
                RecommendedCard(
                    imageVector = Icons.Filled.Share,
                    name = "Sandwich",
                    label = "Starting From",
                    price = 15.50,
                    isAvailable = true,
                    onDetails = {
                        screen(ComposeFoodDeliveryScreen.Details)
                    },
                    onAdd = {
                        Toast.makeText(context, "Add", Toast.LENGTH_SHORT).show()
                    }
                )
            }
        }
    }
}