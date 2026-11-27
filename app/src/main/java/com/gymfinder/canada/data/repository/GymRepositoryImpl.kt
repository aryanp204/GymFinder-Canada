package com.gymfinder.canada.data.repository

import com.gymfinder.canada.data.local.dao.GymDao
import com.gymfinder.canada.data.mapper.toDomainList
import com.gymfinder.canada.data.mapper.toDomainModel
import com.gymfinder.canada.domain.model.Gym
import com.gymfinder.canada.domain.model.GymFilter
import com.gymfinder.canada.domain.model.GymSortOption
import com.gymfinder.canada.domain.repository.GymRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GymRepositoryImpl(
    private val gymDao: GymDao
) : GymRepository {

    override fun getAllGyms(): Flow<List<Gym>> {
        return gymDao.getAllGyms().map { it.toDomainList() }
    }

    override fun getGymsByCity(cityId: String): Flow<List<Gym>> {
        return gymDao.getGymsByCity(cityId).map { it.toDomainList() }
    }

    override fun getGymById(id: String): Flow<Gym?> {
        return gymDao.getGymById(id).map { it?.toDomainModel() }
    }

    override fun getFeaturedGymsByCity(cityId: String): Flow<List<Gym>> {
        return gymDao.getFeaturedGymsByCity(cityId).map { it.toDomainList() }
    }

    override fun searchAndFilterGyms(
        cityId: String,
        query: String,
        filter: GymFilter,
        sortOption: GymSortOption
    ): Flow<List<Gym>> {
        val trimmedQuery = query.trim().lowercase()

        return gymDao.getGymsByCity(cityId).map { entityList ->
            val domainList = entityList.toDomainList()

            // 1. Filter by Search Query
            val searchedList = if (trimmedQuery.isEmpty()) {
                domainList
            } else {
                domainList.filter { gym ->
                    gym.name.lowercase().contains(trimmedQuery) ||
                    gym.cityName.lowercase().contains(trimmedQuery) ||
                    gym.address.lowercase().contains(trimmedQuery) ||
                    gym.category.lowercase().contains(trimmedQuery) ||
                    gym.description.lowercase().contains(trimmedQuery) ||
                    (trimmedQuery == "pool" && gym.hasSwimmingPool) ||
                    (trimmedQuery == "parking" && gym.hasParking) ||
                    (trimmedQuery == "sauna" && gym.hasSauna) ||
                    (trimmedQuery == "24/7" && gym.isOpen24Hours)
                }
            }

            // 2. Filter by Active GymFilter Chips
            val filteredList = searchedList.filter { gym ->
                (!filter.isOpen24Hours || gym.isOpen24Hours) &&
                (!filter.hasSwimmingPool || gym.hasSwimmingPool) &&
                (!filter.hasPersonalTraining || gym.hasPersonalTraining) &&
                (!filter.hasParking || gym.hasParking) &&
                (!filter.hasSauna || gym.hasSauna) &&
                (!filter.isBudget || gym.monthlyPrice <= 40.0)
            }

            // 3. Sort Results
            when (sortOption) {
                GymSortOption.TOP_RATED -> filteredList.sortedWith(
                    compareByDescending<Gym> { it.rating }.thenByDescending { it.reviewCount }
                )
                GymSortOption.PRICE_LOW_TO_HIGH -> filteredList.sortedBy { it.monthlyPrice }
                GymSortOption.PRICE_HIGH_TO_LOW -> filteredList.sortedByDescending { it.monthlyPrice }
                GymSortOption.NAME_A_TO_Z -> filteredList.sortedBy { it.name.lowercase() }
            }
        }
    }

    override fun getGymCountByCity(cityId: String): Flow<Int> {
        return gymDao.getGymCountByCity(cityId)
    }

    override fun getTotalGymCount(): Flow<Int> {
        return gymDao.getTotalGymCount()
    }
}
