package com.gymfinder.canada.data.mapper

import com.gymfinder.canada.data.local.entity.GymEntity
import com.gymfinder.canada.domain.model.Gym

fun GymEntity.toDomainModel(): Gym {
    return Gym(
        id = id,
        name = name,
        cityId = cityId,
        cityName = cityName,
        province = province,
        address = address,
        description = description,
        monthlyPrice = monthlyPrice,
        annualPrice = annualPrice,
        joiningFee = joiningFee,
        rating = rating,
        reviewCount = reviewCount,
        openingHours = openingHours,
        isOpen24Hours = isOpen24Hours,
        hasParking = hasParking,
        hasSwimmingPool = hasSwimmingPool,
        hasSauna = hasSauna,
        hasPersonalTraining = hasPersonalTraining,
        hasGroupClasses = hasGroupClasses,
        hasWiFi = hasWiFi,
        hasShowers = hasShowers,
        isWomenOnly = isWomenOnly,
        category = category,
        imageReference = imageReference,
        phone = phone,
        email = email,
        isFeatured = isFeatured,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

fun Gym.toEntity(): GymEntity {
    return GymEntity(
        id = id,
        name = name,
        cityId = cityId,
        cityName = cityName,
        province = province,
        address = address,
        description = description,
        monthlyPrice = monthlyPrice,
        annualPrice = annualPrice,
        joiningFee = joiningFee,
        rating = rating,
        reviewCount = reviewCount,
        openingHours = openingHours,
        isOpen24Hours = isOpen24Hours,
        hasParking = hasParking,
        hasSwimmingPool = hasSwimmingPool,
        hasSauna = hasSauna,
        hasPersonalTraining = hasPersonalTraining,
        hasGroupClasses = hasGroupClasses,
        hasWiFi = hasWiFi,
        hasShowers = hasShowers,
        isWomenOnly = isWomenOnly,
        category = category,
        imageReference = imageReference,
        phone = phone,
        email = email,
        isFeatured = isFeatured,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

fun List<GymEntity>.toDomainList(): List<Gym> {
    return map { it.toDomainModel() }
}
