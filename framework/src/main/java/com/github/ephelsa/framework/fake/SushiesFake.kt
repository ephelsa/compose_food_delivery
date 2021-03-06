package com.github.ephelsa.framework.fake

import com.github.ephelsa.domain.Category
import com.github.ephelsa.domain.Product

object SushiesFake {

    val Octopussy = Product(
        id = "1",
        categoryType = Category.CategoryType.Sushi,
        name = "Octopussy",
        image = "https://sushilight.com/wp-content/uploads/2019/05/Octopussy.png",
        price = 22.2,
        description = "Pietres Pizza - Enjoy La Pizza",
        isAvailable = true,
        expectedDelivery = 45_000_000,
        ingredients = listOf(
            IngredientsFake.Fish,
            IngredientsFake.Rice,
            IngredientsFake.Avocado,
        )
    )

    val Oma = Product(
        id = "2",
        categoryType = Category.CategoryType.Sushi,
        name = "Sushi Oma",
        image = "https://cupcakestore.com.co/wp-content/uploads/2019/06/sushi1_01.png",
        price = 30.5,
        description = "Pietres Pizza - Enjoy La Pizza",
        isAvailable = true,
        expectedDelivery = 65_000_000,
        ingredients = listOf(
            IngredientsFake.Fish,
            IngredientsFake.Rice,
            IngredientsFake.Avocado,
            IngredientsFake.Tomato
        )
    )
}