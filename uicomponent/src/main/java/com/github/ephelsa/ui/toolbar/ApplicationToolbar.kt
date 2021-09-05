package com.github.ephelsa.ui.toolbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.ephelsa.ui.button.SimpleIconButton
import com.github.ephelsa.ui.button.ThumbImageIcon
import com.github.ephelsa.ui.theme.ExtraLargeSpacing

@Composable
fun ApplicationToolbar(
    leftItem: @Composable () -> Unit,
    rightItem: @Composable () -> Unit
) {

    Surface(
        modifier = Modifier
            .padding(
                horizontal = ExtraLargeSpacing
            )
            .fillMaxWidth()
            .height(80.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            leftItem()
            rightItem()
        }
    }
}

@Preview
@Composable
internal fun ApplicationToolbarPreview() {
    ApplicationToolbar(
        leftItem = {
            ThumbImageIcon(
                imageVector = Icons.Filled.Person,
                contentDescription = "Profile"
            ) {

            }
        },
        rightItem = {
            SimpleIconButton(
                imageVector = Icons.Rounded.Settings,
                contentDescription = "Settings",
                fillBackground = false
            ) {

            }
        }
    )
}