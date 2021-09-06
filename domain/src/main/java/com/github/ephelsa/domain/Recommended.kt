package com.github.ephelsa.domain

data class Recommended(
    val id: String,
    val image: String,
    val name: String,
    val price: Double,
    val isAvailable: Boolean
)
