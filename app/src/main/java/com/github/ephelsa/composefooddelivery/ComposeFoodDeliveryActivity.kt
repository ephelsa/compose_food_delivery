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

@Composable
fun ComposeFoodDeliveryApp() {
    ComposeFoodDeliveryTheme {
        val navController = rememberNavController()
//        val backstackEntry = navController.currentBackStackEntryAsState()
//        val currentScreen = ComposeFoodDeliveryScreen.fromRoute(backstackEntry.value?.destination?.route)

        Scaffold {
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

        }

        composable(ComposeFoodDeliveryScreen.Details.name) {

        }
    }
}