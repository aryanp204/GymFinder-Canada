package com.gymfinder.canada.viewmodel

import androidx.lifecycle.ViewModel
import com.gymfinder.canada.domain.model.City
import com.gymfinder.canada.util.CityDataProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SharedCityViewModel : ViewModel() {

    private val _selectedCity = MutableStateFlow<City>(CityDataProvider.defaultCity)
    val selectedCity: StateFlow<City> = _selectedCity.asStateFlow()

    private val _pendingSearchQuery = MutableStateFlow<String?>("")
    val pendingSearchQuery: StateFlow<String?> = _pendingSearchQuery.asStateFlow()

    fun selectCity(city: City) {
        _selectedCity.value = city
    }

    fun submitSearchQuery(query: String) {
        _pendingSearchQuery.value = query
    }

    fun clearPendingSearchQuery() {
        _pendingSearchQuery.value = null
    }
}
