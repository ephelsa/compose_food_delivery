package com.github.ephelsa.composefooddelivery.ui.home

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import com.github.ephelsa.composefooddelivery.R
import com.github.ephelsa.ui.button.SimpleIconButton
import com.github.ephelsa.ui.theme.ExtraLargeSpacing
import com.github.ephelsa.ui.theme.MediumSpacing1

@Composable
fun HomeBody() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = ExtraLargeSpacing
            )
            .verticalScroll(rememberScrollState())
    ) {
        Title()
        Spacer(Modifier.height(ExtraLargeSpacing))
        Search()
        Spacer(Modifier.height(ExtraLargeSpacing))
        CategorySection()
        Spacer(Modifier.height(ExtraLargeSpacing))
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

        Spacer(Modifier.height(ExtraLargeSpacing))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(MediumSpacing1)
        ) {
            items(10) {
                Text(text = it.toString())
            }
        }
    }
}

@Composable
private fun RecommendedSection() {
    Column {
        Text(
            text = stringResource(R.string.sectionLabel_recommended),
            style = MaterialTheme.typography.h6
        )

        Spacer(Modifier.height(ExtraLargeSpacing))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(MediumSpacing1)
        ) {
            items(10) {
                Text(text = it.toString())
            }
        }
    }
}