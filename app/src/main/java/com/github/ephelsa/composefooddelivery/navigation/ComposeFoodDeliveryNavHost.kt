package com.github.ephelsa.composefooddelivery.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import coil.annotation.ExperimentalCoilApi
import com.github.ephelsa.composefooddelivery.ui.details.DetailsScreen
import com.github.ephelsa.composefooddelivery.ui.details.DetailsViewModel
import com.github.ephelsa.composefooddelivery.ui.extras.UnderConstructionBody
import com.github.ephelsa.composefooddelivery.ui.home.HomeBody
import com.github.ephelsa.composefooddelivery.ui.home.HomeViewModel

@ExperimentalAnimationApi
@ExperimentalCoilApi
@Composable
fun ComposeFoodDeliveryNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    val homeViewModel = viewModel<HomeViewModel>()
    val detailsViewModel = viewModel<DetailsViewModel>()

    NavHost(
        navController = navController,
        startDestination = ComposeFoodDeliveryRouter.Home.name,
        modifier = modifier
    ) {
        composable(ComposeFoodDeliveryRouter.Home.name) {
            HomeBody(
                viewModel = homeViewModel,
                onRecommended = detailsViewModel::getDetails,
                onAddProduct = homeViewModel::addProduct,
                navigate = { navController.navigate(it.name) }
            )
        }

        composable(ComposeFoodDeliveryRouter.Favorites.name) {
            UnderConstructionBody()
        }

        composable(ComposeFoodDeliveryRouter.Location.name) {
            UnderConstructionBody()
        }

        composable(ComposeFoodDeliveryRouter.ShoppingCart.name) {
            UnderConstructionBody()
        }

        composable(ComposeFoodDeliveryRouter.Profile.name) {
            UnderConstructionBody()
        }

        composable(ComposeFoodDeliveryRouter.Settings.name) {
            UnderConstructionBody()
        }

        composable(ComposeFoodDeliveryRouter.Details.name) {
            DetailsScreen(
                viewModel = detailsViewModel,
                onBackClick = {
                    navController.popBackStack()

                },
                onAddClick = detailsViewModel::addProduct
            )
        }
    }
}