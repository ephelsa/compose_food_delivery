package com.github.ephelsa.composefooddelivery.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.github.ephelsa.composefooddelivery.route.ComposeFoodDeliveryScreen
import com.github.ephelsa.composefooddelivery.ui.details.DetailsScreen
import com.github.ephelsa.composefooddelivery.ui.details.DetailsViewModel
import com.github.ephelsa.composefooddelivery.ui.extras.NavigationFoodDeliveryBottomBar
import com.github.ephelsa.composefooddelivery.ui.extras.UnderConstructionBody
import com.github.ephelsa.composefooddelivery.ui.extras.UserFoodDeliveryToolbar
import com.github.ephelsa.composefooddelivery.ui.home.HomeBody
import com.github.ephelsa.composefooddelivery.ui.home.HomeViewModel
import com.github.ephelsa.ui.theme.ComposeFoodDeliveryTheme

@ExperimentalCoilApi
@ExperimentalAnimationApi
@Composable
fun ComposeFoodDeliveryApp() {
    ComposeFoodDeliveryTheme {
        val navController = rememberNavController()
        val backstackEntry = navController.currentBackStackEntryAsState()
        val currentScreen = ComposeFoodDeliveryScreen.fromRoute(backstackEntry.value?.destination?.route)

        Scaffold(
            topBar = {
                if (currentScreen.toolbar)
                    UserFoodDeliveryToolbar(
                        onProfileClick = {
                            navController.navigate(ComposeFoodDeliveryScreen.Profile.name)
                        },
                        onSettingsClick = {
                            navController.navigate(ComposeFoodDeliveryScreen.Settings.name)
                        }
                    )
            },
            bottomBar = {
                if (currentScreen.bottomBar) {
                    val options = ComposeFoodDeliveryScreen
                        .values()
                        .filter(ComposeFoodDeliveryScreen::isBottomBarOption)

                    NavigationFoodDeliveryBottomBar(currentScreen, options) { screen ->
                        navController.navigate(screen.name)
                    }
                }
            }
        ) {
            ComposeFoodDeliveryNavHost(navController, modifier = Modifier.padding(it))
        }
    }
}

@ExperimentalAnimationApi
@ExperimentalCoilApi
@Composable
fun ComposeFoodDeliveryNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    val homeViewModel = viewModel<HomeViewModel>()
    val detailsViewModel = viewModel<DetailsViewModel>()

    NavHost(
        navController = navController,
        startDestination = ComposeFoodDeliveryScreen.Home.name,
        modifier = modifier
    ) {
        composable(ComposeFoodDeliveryScreen.Home.name) {
            HomeBody(homeViewModel) { navController.navigate(it.name) }
        }

        composable(ComposeFoodDeliveryScreen.Favorites.name) {
            UnderConstructionBody()
        }

        composable(ComposeFoodDeliveryScreen.Location.name) {
            UnderConstructionBody()
        }

        composable(ComposeFoodDeliveryScreen.ShoppingCart.name) {
            UnderConstructionBody()
        }

        composable(ComposeFoodDeliveryScreen.Profile.name) {
            UnderConstructionBody()
        }

        composable(ComposeFoodDeliveryScreen.Settings.name) {
            UnderConstructionBody()
        }

        composable(ComposeFoodDeliveryScreen.Details.name) {
            DetailsScreen(detailsViewModel) {
                navController.popBackStack()
            }
        }
    }
}