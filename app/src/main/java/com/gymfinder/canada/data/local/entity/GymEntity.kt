package com.gymfinder.canada.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "gyms",
    indices = [
        Index("cityId"),
        Index("category"),
        Index("isFeatured")
    ]
)
data class GymEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val cityId: String,
    val cityName: String,
    val province: String,
    val address: String,
    val description: String,
    val monthlyPrice: Double,
    val annualPrice: Double,
    val joiningFee: Double,
    val rating: Double,
    val reviewCount: Int,
    val openingHours: String,
    val isOpen24Hours: Boolean,
    val hasParking: Boolean,
    val hasSwimmingPool: Boolean,
    val hasSauna: Boolean,
    val hasPersonalTraining: Boolean,
    val hasGroupClasses: Boolean,
    val hasWiFi: Boolean,
    val hasShowers: Boolean,
    val isWomenOnly: Boolean,
    val category: String,
    val imageReference: String,
    val phone: String,
    val email: String,
    val isFeatured: Boolean,
    val createdAt: Long,
    val updatedAt: Long
)
