package com.github.ephelsa.composefooddelivery

/**
 * This class contains all the screen's routes for the application!
 */
enum class ComposeFoodDeliveryScreen {
    Home,
    Details;

    companion object {
        fun fromRoute(route: String?): ComposeFoodDeliveryScreen = when (route?.substringBefore("/")) {
            Home.name -> Home
            Details.name -> Details
            else -> throw IllegalArgumentException("Route $route is not recognized!")
        }
    }
}

