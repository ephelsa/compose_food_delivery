package com.github.ephelsa.composefooddelivery.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.github.ephelsa.composefooddelivery.navigation.ComposeFoodDeliveryNavHost
import com.github.ephelsa.composefooddelivery.navigation.ComposeFoodDeliveryRouter
import com.github.ephelsa.composefooddelivery.ui.extras.NavigationFoodDeliveryBottomBar
import com.github.ephelsa.composefooddelivery.ui.extras.UserFoodDeliveryToolbar
import com.github.ephelsa.composefooddelivery.ui.extras.VisibleToolbar
import com.github.ephelsa.ui.theme.ComposeFoodDeliveryTheme
import com.github.ephelsa.ui.theme.LargeSpacing

@ExperimentalCoilApi
@ExperimentalAnimationApi
@Composable
fun ComposeFoodDeliveryApp() {
    ComposeFoodDeliveryTheme {
        val navController = rememberNavController()
        val backstackEntry = navController.currentBackStackEntryAsState()
        val currentScreen = ComposeFoodDeliveryRouter.fromRoute(backstackEntry.value?.destination?.route)

        Scaffold(
            topBar = {
                if (currentScreen.toolbar)
                    UserFoodDeliveryToolbar(
                        onProfileClick = {
                            navController.navigate(ComposeFoodDeliveryRouter.Profile.name)
                        },
                        onSettingsClick = {
                            navController.navigate(ComposeFoodDeliveryRouter.Settings.name)
                        }
                    )
            },
            bottomBar = {
                if (currentScreen.bottomBar) {
                    val options = ComposeFoodDeliveryRouter
                        .values()
                        .filter(ComposeFoodDeliveryRouter::isBottomBarOption)

                    NavigationFoodDeliveryBottomBar(currentScreen, options) { screen ->
                        navController.navigate(screen.name)
                    }
                }
            }
        ) { paddings ->
            val modifier = if (currentScreen.bottomBar) {
                Modifier.padding(
                    bottom = VisibleToolbar + LargeSpacing,
                    top = paddings.calculateTopPadding()
                )
            } else {
                Modifier.padding(paddings)
            }

            ComposeFoodDeliveryNavHost(
                navController = navController,
                modifier = modifier
            )
        }
    }
}
