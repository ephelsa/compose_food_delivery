package com.github.ephelsa.domain

data class Category(
    val categoryType: CategoryType,
    val name: String,
    val image: String,
) {
    enum class CategoryType {
        Burger,
        Pizza,
        Sushi,
        Drink
    }
}
