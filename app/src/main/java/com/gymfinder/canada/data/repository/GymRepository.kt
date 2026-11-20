package com.gymfinder.canada.data.repository

import com.gymfinder.canada.domain.model.Gym
import kotlinx.coroutines.flow.Flow

interface GymRepository {
    fun getFeaturedGyms(): Flow<List<Gym>>
    fun getGymById(id: String): Flow<Gym?>
}
