package com.gymfinder.canada.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.gymfinder.canada.di.AppContainerProvider
import com.gymfinder.canada.domain.model.City
import com.gymfinder.canada.domain.model.Gym
import com.gymfinder.canada.domain.model.GymFilter
import com.gymfinder.canada.domain.model.GymSortOption
import com.gymfinder.canada.domain.repository.GymRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ExploreUiState(
    val gyms: List<Gym> = emptyList(),
    val searchQuery: String = "",
    val filter: GymFilter = GymFilter(),
    val sortOption: GymSortOption = GymSortOption.TOP_RATED,
    val isLoading: Boolean = false,
    val error: String? = null
)

class ExploreViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository: GymRepository = AppContainerProvider.getContainer(application).gymRepository

    private val _uiState = MutableStateFlow(ExploreUiState())
    val uiState: StateFlow<ExploreUiState> = _uiState.asStateFlow()

    private var currentCityId: String = ""

    fun updateSelectedCity(city: City) {
        currentCityId = city.id
        loadGyms()
    }

    fun onQueryChanged(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
        loadGyms()
    }

    fun toggleFilter247() {
        _uiState.update { it.copy(filter = it.filter.copy(isOpen24Hours = !it.filter.isOpen24Hours)) }
        loadGyms()
    }

    fun toggleFilterPool() {
        _uiState.update { it.copy(filter = it.filter.copy(hasSwimmingPool = !it.filter.hasSwimmingPool)) }
        loadGyms()
    }

    fun toggleFilterTraining() {
        _uiState.update { it.copy(filter = it.filter.copy(hasPersonalTraining = !it.filter.hasPersonalTraining)) }
        loadGyms()
    }

    fun toggleFilterParking() {
        _uiState.update { it.copy(filter = it.filter.copy(hasParking = !it.filter.hasParking)) }
        loadGyms()
    }

    fun toggleFilterSauna() {
        _uiState.update { it.copy(filter = it.filter.copy(hasSauna = !it.filter.hasSauna)) }
        loadGyms()
    }

    fun toggleFilterBudget() {
        _uiState.update { it.copy(filter = it.filter.copy(isBudget = !it.filter.isBudget)) }
        loadGyms()
    }

    fun clearFilters() {
        _uiState.update { it.copy(filter = GymFilter(), searchQuery = "") }
        loadGyms()
    }

    fun setSortOption(sortOption: GymSortOption) {
        _uiState.update { it.copy(sortOption = sortOption) }
        loadGyms()
    }

    private fun loadGyms() {
        if (currentCityId.isEmpty()) return
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val currentState = _uiState.value
            repository.searchAndFilterGyms(
                cityId = currentCityId,
                query = currentState.searchQuery,
                filter = currentState.filter,
                sortOption = currentState.sortOption
            ).collectLatest { resultGyms ->
                _uiState.update {
                    it.copy(
                        gyms = resultGyms,
                        isLoading = false
                    )
                }
            }
        }
    }
}
