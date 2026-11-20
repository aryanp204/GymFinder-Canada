package com.gymfinder.canada.domain.model

data class Gym(
    val id: String,
    val name: String,
    val rating: Double,
    val reviewCount: Int,
    val priceMonthly: Double,
    val city: String = "Toronto, Ontario",
    val imageResId: Int,
    val tags: List<String> = emptyList(),
    val isFavorite: Boolean = false
)
