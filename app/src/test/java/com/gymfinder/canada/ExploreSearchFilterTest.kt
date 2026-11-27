package com.gymfinder.canada

import com.gymfinder.canada.data.local.seed.GymSeedData
import com.gymfinder.canada.data.mapper.toDomainList
import com.gymfinder.canada.domain.model.GymFilter
import com.gymfinder.canada.domain.model.GymSortOption
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ExploreSearchFilterTest {

    private val allGyms = GymSeedData.getInitialGyms().toDomainList()
    private val torontoGyms = allGyms.filter { it.cityId == "city_toronto" }

    @Test
    fun seedData_totalCount_isExactly96() {
        assertEquals(96, allGyms.size)
    }

    @Test
    fun seedData_torontoCount_isExactly8() {
        assertEquals(8, torontoGyms.size)
    }

    @Test
    fun searchByGymName_returnsMatchingGym() {
        val query = "ironpeak"
        val results = torontoGyms.filter { it.name.lowercase().contains(query) }

        assertEquals(1, results.size)
        assertEquals("IronPeak Fitness", results[0].name)
    }

    @Test
    fun filterBy24Hours_returnsOnly247Gyms() {
        val filter = GymFilter(isOpen24Hours = true)
        val results = torontoGyms.filter { !filter.isOpen24Hours || it.isOpen24Hours }

        assertTrue(results.all { it.isOpen24Hours })
    }

    @Test
    fun filterByBudget_returnsGymsUnder40Dollars() {
        val filter = GymFilter(isBudget = true)
        val results = torontoGyms.filter { !filter.isBudget || it.monthlyPrice <= 40.0 }

        assertTrue(results.all { it.monthlyPrice <= 40.0 })
    }

    @Test
    fun sortByPriceLowToHigh_returnsAscendingPrices() {
        val sorted = torontoGyms.sortedBy { it.monthlyPrice }

        for (i in 0 until sorted.size - 1) {
            assertTrue(sorted[i].monthlyPrice <= sorted[i + 1].monthlyPrice)
        }
    }

    @Test
    fun sortByNameAZ_returnsAlphabeticalOrder() {
        val sorted = torontoGyms.sortedBy { it.name.lowercase() }

        for (i in 0 until sorted.size - 1) {
            assertTrue(sorted[i].name.lowercase() <= sorted[i + 1].name.lowercase())
        }
    }
}
