package com.github.ephelsa.domain

data class Recommended(
    val id: String,
    val categoryType: Category.CategoryType,
    val image: String,
    val name: String,
    val price: Double,
    val isAvailable: Boolean
) : ProductWithID {

    override val realID: String
        get() = "${categoryType.name}_$id"
}
