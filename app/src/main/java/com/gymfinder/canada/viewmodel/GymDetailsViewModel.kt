package com.gymfinder.canada.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.gymfinder.canada.di.AppContainerProvider
import com.gymfinder.canada.domain.model.Gym
import com.gymfinder.canada.domain.repository.GymRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class GymDetailsUiState(
    val gym: Gym? = null,
    val selectedTab: Int = 0,
    val isLoading: Boolean = false,
    val error: String? = null
)

class GymDetailsViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository: GymRepository = AppContainerProvider.getContainer(application).gymRepository

    private val _uiState = MutableStateFlow(GymDetailsUiState())
    val uiState: StateFlow<GymDetailsUiState> = _uiState.asStateFlow()

    fun loadGymDetails(gymId: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            repository.getGymById(gymId).collectLatest { gym ->
                if (gym != null) {
                    _uiState.update {
                        it.copy(
                            gym = gym,
                            isLoading = false
                        )
                    }
                } else {
                    _uiState.update {
                        it.copy(
                            gym = null,
                            isLoading = false,
                            error = "Gym not found."
                        )
                    }
                }
            }
        }
    }

    fun selectTab(tabIndex: Int) {
        _uiState.update { it.copy(selectedTab = tabIndex) }
    }
}
