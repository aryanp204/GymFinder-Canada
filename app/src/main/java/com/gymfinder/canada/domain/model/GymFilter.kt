package com.gymfinder.canada.domain.model

data class GymFilter(
    val isOpen24Hours: Boolean = false,
    val hasSwimmingPool: Boolean = false,
    val hasPersonalTraining: Boolean = false,
    val hasParking: Boolean = false,
    val hasSauna: Boolean = false,
    val isBudget: Boolean = false
) {
    val isActive: Boolean
        get() = isOpen24Hours || hasSwimmingPool || hasPersonalTraining || hasParking || hasSauna || isBudget
}
