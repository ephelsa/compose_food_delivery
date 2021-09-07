package com.github.ephelsa.composefooddelivery.ui.extras

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.ephelsa.composefooddelivery.ComposeFoodDeliveryViewModel
import com.github.ephelsa.composefooddelivery.navigation.ComposeFoodDeliveryRouter
import com.github.ephelsa.ui.button.ExtendableIconButton
import com.github.ephelsa.ui.theme.LargeSpacing

private val ToolbarHeight = 100.dp
internal val VisibleToolbar = (ToolbarHeight.value / 2).dp

@ExperimentalAnimationApi
@Composable
fun NavigationFoodDeliveryBottomBar(
    currentScreen: ComposeFoodDeliveryRouter,
    options: List<ComposeFoodDeliveryRouter>,
    modifier: Modifier = Modifier,
    onOptionClick: (ComposeFoodDeliveryRouter) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(ToolbarHeight)
            .background(
                brush = Brush.verticalGradient(
                    colorStops = arrayOf(
                        0.2f to Color.Transparent,
                        0.8f to MaterialTheme.colors.onSecondary,
                    )
                ),
                alpha = 0.2f
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
            .height(VisibleToolbar)
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
    currentScreen: ComposeFoodDeliveryRouter,
    options: List<ComposeFoodDeliveryRouter>,
    onOptionClick: (ComposeFoodDeliveryRouter) -> Unit,
    viewModel: ComposeFoodDeliveryViewModel = viewModel(),
) {
    val quantityItems by viewModel.onProductsQuantity.collectAsState()

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
                text = stringResource(id = option.strRes!!),
                number = if (option == ComposeFoodDeliveryRouter.ShoppingCart) quantityItems else 0
            ) {
                onOptionClick(option)
            }
        }
    }
}
