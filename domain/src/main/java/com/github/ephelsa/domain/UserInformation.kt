package com.github.ephelsa.domain

data class UserInformation(
    val image: String,
    val firstName: String,
    val secondName: String?,
    val firstSurname: String,
    val secondSurname: String?,
    val age: Int,
    val locationInformation: LocationInformation,
    val phoneInformation: PhoneInformation?
)
