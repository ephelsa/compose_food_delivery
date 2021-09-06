package com.github.ephelsa.domain

data class Product(
    val id: String,
    val image: String,
    val price: Double,
    val description: String?,
    val expectedDelivery: Long,
    val ingredients: List<Ingredients>
)
