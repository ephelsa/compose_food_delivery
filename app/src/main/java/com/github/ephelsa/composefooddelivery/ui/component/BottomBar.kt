package com.github.ephelsa.composefooddelivery.ui.component

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.github.ephelsa.composefooddelivery.ComposeFoodDeliveryScreen
import com.github.ephelsa.ui.button.ExtendableIconButton
import com.github.ephelsa.ui.theme.GreenWhite
import com.github.ephelsa.ui.theme.LargeSpacing

private val ToolbarHeight = 100.dp

@ExperimentalAnimationApi
@Composable
fun NavigationFoodDeliveryBottomBar(
    currentScreen: ComposeFoodDeliveryScreen,
    options: List<ComposeFoodDeliveryScreen>,
    onOptionClick: (ComposeFoodDeliveryScreen) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(ToolbarHeight)
            .padding(bottom = LargeSpacing)
            .background(
                brush = Brush.verticalGradient(
                    colorStops = arrayOf(
                        0.0f to Color.Transparent,
                        0.6f to GreenWhite,
                    )
                )
            ),
        contentAlignment = Alignment.BottomCenter
    ) {
        BottomLimit()
        Options(currentScreen, options, onOptionClick)
    }
}

@Composable
private fun BottomLimit() {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height((ToolbarHeight.value / 2).dp)
            .clip(
                MaterialTheme
                    .shapes
                    .large
                    .copy(bottomEnd = ZeroCornerSize, bottomStart = ZeroCornerSize)
            )
            .background(MaterialTheme.colors.surface)
    )
}

@ExperimentalAnimationApi
@Composable
private fun Options(
    currentScreen: ComposeFoodDeliveryScreen,
    options: List<ComposeFoodDeliveryScreen>,
    onOptionClick: (ComposeFoodDeliveryScreen) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = LargeSpacing)
            .animateContentSize(),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        options.map { option ->
            ExtendableIconButton(
                isExtended = option == currentScreen,
                imageVector = option.icon!!,
                text = stringResource(id = option.strRes!!)
            ) {
                onOptionClick(option)
            }
        }
    }
}
