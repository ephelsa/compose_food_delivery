package com.github.ephelsa.composefooddelivery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.github.ephelsa.composefooddelivery.ui.component.NavigationFoodDeliveryBottomBar
import com.github.ephelsa.composefooddelivery.ui.component.UserFoodDeliveryToolbar
import com.github.ephelsa.composefooddelivery.ui.control.UnderConstructionBody
import com.github.ephelsa.composefooddelivery.ui.home.HomeBody
import com.github.ephelsa.ui.theme.ComposeFoodDeliveryTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@ExperimentalAnimationApi
class ComposeFoodDeliveryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeFoodDeliveryApp()
        }
    }
}

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

@Composable
fun ComposeFoodDeliveryNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = ComposeFoodDeliveryScreen.Home.name,
        modifier = modifier
    ) {
        composable(ComposeFoodDeliveryScreen.Home.name) {
            HomeBody { navController.navigate(it.name) }
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
            UnderConstructionBody()
        }
    }
}