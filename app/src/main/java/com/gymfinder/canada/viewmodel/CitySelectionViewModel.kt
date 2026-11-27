package com.gymfinder.canada.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.gymfinder.canada.di.AppContainerProvider
import com.gymfinder.canada.domain.model.City
import com.gymfinder.canada.domain.repository.GymRepository
import com.gymfinder.canada.util.CityDataProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class CityItem(
    val city: City,
    val gymCount: Int = 8
)

data class CitySelectionUiState(
    val cities: List<CityItem> = emptyList(),
    val searchQuery: String = "",
    val isLoading: Boolean = false
)

class CitySelectionViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository: GymRepository = AppContainerProvider.getContainer(application).gymRepository

    private val _uiState = MutableStateFlow(CitySelectionUiState())
    val uiState: StateFlow<CitySelectionUiState> = _uiState.asStateFlow()

    private var allCitiesWithCounts = listOf<CityItem>()

    init {
        loadCityData()
    }

    private fun loadCityData() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val cityList = CityDataProvider.cities.map { city ->
                CityItem(city = city, gymCount = 8)
            }
            allCitiesWithCounts = cityList
            filterCities(_uiState.value.searchQuery)
        }
    }

    fun onSearchQueryChanged(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
        filterCities(query)
    }

    private fun filterCities(query: String) {
        val trimmed = query.trim().lowercase()
        val filtered = if (trimmed.isEmpty()) {
            allCitiesWithCounts
        } else {
            allCitiesWithCounts.filter {
                it.city.name.lowercase().contains(trimmed) ||
                it.city.province.lowercase().contains(trimmed)
            }
        }
        _uiState.update {
            it.copy(
                cities = filtered,
                isLoading = false
            )
        }
    }
}
