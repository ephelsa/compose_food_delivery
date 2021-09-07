package com.github.ephelsa.framework.fake

import com.github.ephelsa.domain.Category
import com.github.ephelsa.domain.Product

object BurgersFake {
    val RedRobinsAvocado = Product(
        id = "1",
        categoryType = Category.CategoryType.Burger,
        name = "Madlove Burger",
        image = "https://images.ctfassets.net/oewsurrg31ok/4kKWpoEX6UuCGdDVaHSGpQ/383c8f86303063ed8d2f1fe971a7659c/2020_Burger_Madlove.png?w=1240&q=55",
        price = 12.2,
        description = "Red Robin's creation and they said this is the most completed burger!",
        isAvailable = true,
        expectedDelivery = 20_000_000,
        ingredients = listOf(
            IngredientsFake.BurgerBread,
            IngredientsFake.Tomato,
            IngredientsFake.BurgerBeef,
            IngredientsFake.Cheese,
            IngredientsFake.Beacon,
            IngredientsFake.Avocado,
            IngredientsFake.Lettuce,
        )
    )

    val TheOriginalGrassFedBurger = Product(
        id = "2",
        categoryType = Category.CategoryType.Burger,
        name = "White Cheddar Burger",
        image = "https://www.burgerlounge.com/perch/resources/menuphotos/2020-bl-menu-lounge-white-cheddar.png",
        price = 10.5,
        description = "Burger Lounge: The original Grass-Fed Burger",
        isAvailable = true,
        expectedDelivery = 15_000_000,
        ingredients = listOf(
            IngredientsFake.BurgerBread,
            IngredientsFake.Tomato,
            IngredientsFake.BurgerBeef,
            IngredientsFake.Cheese,
            IngredientsFake.Onions,
            IngredientsFake.Lettuce,
        )
    )

    val FatBurgerOriginal = Product(
        id = "3",
        categoryType = Category.CategoryType.Burger,
        name = "King Burger",
        image = "https://cdn-cmjom.nitrocdn.com/FpMsHpAgoVrRMnuAdmBhGkyiizdsWlSU/assets/static/optimized/rev-0223ca3/wp-content/uploads/2015/07/king-burger-541x633.png",
        price = 20.8,
        description = "The Biggest, Juiciest Burgers You'll Ever Taste - FatBurger",
        isAvailable = true,
        expectedDelivery = 30_000_000,
        ingredients = listOf(
            IngredientsFake.BurgerBread,
            IngredientsFake.Tomato,
            IngredientsFake.BurgerBeef,
            IngredientsFake.Cheese,
            IngredientsFake.Onions,
            IngredientsFake.Lettuce,
        )
    )
}