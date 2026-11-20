package com.gymfinder.canada.data.repository

import com.gymfinder.canada.R
import com.gymfinder.canada.domain.model.Gym
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class GymRepositoryImpl : GymRepository {

    private val sampleGyms = listOf(
        Gym(
            id = "gym_1",
            name = "IronPeak Fitness",
            rating = 4.8,
            reviewCount = 230,
            priceMonthly = 39.99,
            city = "Toronto, Ontario",
            imageResId = R.drawable.ic_gym_ironpeak,
            tags = listOf("24/7", "Parking", "Training")
        ),
        Gym(
            id = "gym_2",
            name = "UrbanFit Studio",
            rating = 4.6,
            reviewCount = 197,
            priceMonthly = 44.99,
            city = "Toronto, Ontario",
            imageResId = R.drawable.ic_gym_urbanfit,
            tags = listOf("Pool", "Sauna", "Classes")
        ),
        Gym(
            id = "gym_3",
            name = "EliteCore Athletics",
            rating = 4.7,
            reviewCount = 165,
            priceMonthly = 49.99,
            city = "Toronto, Ontario",
            imageResId = R.drawable.ic_gym_elitecore,
            tags = listOf("24/7", "Training", "Wi-Fi")
        ),
        Gym(
            id = "gym_4",
            name = "NorthStar Training",
            rating = 4.5,
            reviewCount = 142,
            priceMonthly = 34.99,
            city = "Toronto, Ontario",
            imageResId = R.drawable.ic_gym_northstar,
            tags = listOf("Parking", "Classes", "Showers")
        )
    )

    override fun getFeaturedGyms(): Flow<List<Gym>> {
        return flowOf(sampleGyms)
    }

    override fun getGymById(id: String): Flow<Gym?> {
        return flowOf(sampleGyms.find { it.id == id })
    }
}
