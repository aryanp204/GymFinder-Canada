package com.gymfinder.canada.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.gymfinder.canada.di.AppContainerProvider
import com.gymfinder.canada.domain.model.City
import com.gymfinder.canada.domain.repository.GymRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository: GymRepository = AppContainerProvider.getContainer(application).gymRepository

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun updateSelectedCity(city: City) {
        _uiState.update { it.copy(selectedCity = city.displayName) }
        loadFeaturedGymsForCity(city.id)
    }

    private fun loadFeaturedGymsForCity(cityId: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            repository.getFeaturedGymsByCity(cityId).collectLatest { gyms ->
                _uiState.update {
                    it.copy(
                        featuredGyms = gyms,
                        isLoading = false
                    )
                }
            }
        }
    }

    fun onSearchQueryChanged(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
    }
}
