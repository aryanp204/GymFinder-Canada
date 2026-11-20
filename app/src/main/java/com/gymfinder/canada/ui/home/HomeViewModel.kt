package com.gymfinder.canada.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gymfinder.canada.data.repository.GymRepository
import com.gymfinder.canada.data.repository.GymRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: GymRepository
) : ViewModel() {

    // Parameterless constructor for ViewModelProvider instantiation
    constructor() : this(GymRepositoryImpl())

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadFeaturedGyms()
    }

    private fun loadFeaturedGyms() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            repository.getFeaturedGyms().collect { gyms ->
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

    fun onCitySelected(city: String) {
        _uiState.update { it.copy(selectedCity = city) }
    }
}
