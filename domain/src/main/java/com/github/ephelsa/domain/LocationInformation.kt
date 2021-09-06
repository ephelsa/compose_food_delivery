package com.github.ephelsa.domain

data class LocationInformation(
    val city: String,
    val state: String,
    val country: String,
    val address: String,
    val extras: List<String>
)
