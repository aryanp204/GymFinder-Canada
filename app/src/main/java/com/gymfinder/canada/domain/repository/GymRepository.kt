package com.gymfinder.canada.domain.repository

import com.gymfinder.canada.domain.model.Gym
import com.gymfinder.canada.domain.model.GymFilter
import com.gymfinder.canada.domain.model.GymSortOption
import kotlinx.coroutines.flow.Flow

interface GymRepository {
    fun getAllGyms(): Flow<List<Gym>>
    fun getGymsByCity(cityId: String): Flow<List<Gym>>
    fun getGymById(id: String): Flow<Gym?>
    fun getFeaturedGymsByCity(cityId: String): Flow<List<Gym>>
    fun searchAndFilterGyms(
        cityId: String,
        query: String,
        filter: GymFilter,
        sortOption: GymSortOption
    ): Flow<List<Gym>>
    fun getGymCountByCity(cityId: String): Flow<Int>
    fun getTotalGymCount(): Flow<Int>
}
