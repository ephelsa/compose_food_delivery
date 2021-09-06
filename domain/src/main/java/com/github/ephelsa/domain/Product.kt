package com.github.ephelsa.domain

data class Product(
    val id: String,
    val categoryType: Category.CategoryType,
    val name: String,
    val image: String,
    val price: Double,
    val description: String?,
    val isAvailable: Boolean,
    val expectedDelivery: Long,
    val ingredients: List<Ingredient>
) : ProductWithID {

    override fun getRealID(): String = "${categoryType.name}_$id"

    fun toRecommended(): Recommended {
        return Recommended(
            id = id,
            categoryType = categoryType,
            image = image,
            name = name,
            price = price,
            isAvailable = isAvailable
        )
    }
}
