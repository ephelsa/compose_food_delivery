package com.github.ephelsa.composefooddelivery.ui.home

import android.widget.Toast
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import com.github.ephelsa.composefooddelivery.R
import com.github.ephelsa.ui.button.SimpleIconButton
import com.github.ephelsa.ui.card.CategoryCard
import com.github.ephelsa.ui.card.RecommendedCard
import com.github.ephelsa.ui.theme.ExtraHugeSpacing
import com.github.ephelsa.ui.theme.HugeSpacing
import com.github.ephelsa.ui.theme.LargeSpacing

@Composable
fun HomeBody() {
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
        CategorySection()
        Spacer(Modifier.height(ExtraHugeSpacing))
        RecommendedSection()
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

@Composable
private fun CategorySection() {
    Column {
        Text(
            text = stringResource(R.string.sectionLabel_categories),
            style = MaterialTheme.typography.h5
        )

        Spacer(Modifier.height(ExtraHugeSpacing))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(LargeSpacing)
        ) {
            items(10) {
                CategoryCard(
                    text = "Sandwich",
                    imageVector = Icons.Filled.Email,
                    isSelected = true
                ) {

                }
            }
        }
    }
}

@Composable
private fun RecommendedSection() {
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
                        Toast.makeText(context, "Details", Toast.LENGTH_SHORT).show()
                    },
                    onAdd = {
                        Toast.makeText(context, "Add", Toast.LENGTH_SHORT).show()
                    }
                )
            }
        }
    }
}