package com.gymfinder.canada.ui.home

import com.gymfinder.canada.domain.model.Gym

data class HomeUiState(
    val selectedCity: String = "Toronto, Ontario",
    val searchQuery: String = "",
    val featuredGyms: List<Gym> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
