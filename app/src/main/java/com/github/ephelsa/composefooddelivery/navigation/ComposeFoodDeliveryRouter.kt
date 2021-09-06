package com.github.ephelsa.composefooddelivery.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.github.ephelsa.composefooddelivery.R

/**
 * This class contains all the screen's routes for the application!
 */
enum class ComposeFoodDeliveryRouter(
    val toolbar: Boolean,
    val bottomBar: Boolean,
    val isBottomBarOption: Boolean,
    val icon: ImageVector? = null,
    @StringRes val strRes: Int? = null
) {
    Home(
        toolbar = true,
        bottomBar = true,
        isBottomBarOption = true,
        icon = Icons.Rounded.Home,
        strRes = R.string.bottomBarOption_home
    ),
    Favorites(
        toolbar = true,
        bottomBar = true,
        isBottomBarOption = true,
        icon = Icons.Rounded.Favorite,
        strRes = R.string.bottomBarOption_favorites
    ),
    Location(
        toolbar = true,
        bottomBar = true,
        isBottomBarOption = true,
        icon = Icons.Rounded.LocationOn,
        strRes = R.string.bottomBarOption_location
    ),
    ShoppingCart(
        toolbar = true,
        bottomBar = true,
        isBottomBarOption = true,
        icon = Icons.Rounded.ShoppingCart,
        strRes = R.string.bottomBarOption_shoppingCart
    ),
    Profile(
        toolbar = true,
        bottomBar = true,
        isBottomBarOption = true,
        icon = Icons.Rounded.Person,
        strRes = R.string.bottomBarOption_profile
    ),
    Settings(
        toolbar = false,
        bottomBar = false,
        isBottomBarOption = false,
    ),
    Details(
        toolbar = false,
        bottomBar = false,
        isBottomBarOption = false,
    );

    companion object {
        fun fromRoute(route: String?): ComposeFoodDeliveryRouter = when (route?.substringBefore("/")) {
            Home.name, null -> Home
            Favorites.name -> Favorites
            Location.name -> Location
            ShoppingCart.name -> ShoppingCart
            Profile.name -> Profile
            Settings.name -> Settings
            Details.name -> Details
            else -> throw IllegalArgumentException("Route $route is not recognized!")
        }
    }
}

