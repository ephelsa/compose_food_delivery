package com.github.ephelsa.framework

import com.github.ephelsa.domain.Category
import com.github.ephelsa.domain.Product

object PizzasMock {
    val Napolitana = Product(
        id = "1",
        categoryType = Category.CategoryType.Pizza,
        name = "Napolitana",
        image = "https://pietres.com/wp-content/uploads/2020/12/TOMATE-Y-ALBHACA-580x580-min.png",
        price = 14.8,
        description = "Pietres Pizza - Enjoy La Pizza",
        isAvailable = true,
        expectedDelivery = 32_000_000,
        ingredients = listOf(
            IngredientsMock.Tomato,
            IngredientsMock.Cheese,
            IngredientsMock.Basil,
        )
    )
}