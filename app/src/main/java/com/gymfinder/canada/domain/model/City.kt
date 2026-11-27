package com.gymfinder.canada.domain.model

data class City(
    val id: String,
    val name: String,
    val province: String,
    val latitude: Double,
    val longitude: Double
) {
    val displayName: String
        get() = "$name, $province"
}
