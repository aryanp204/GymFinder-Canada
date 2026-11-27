package com.gymfinder.canada.ui.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Home : Screen("home")
    object Explore : Screen("explore?query={query}") {
        fun createRoute(query: String? = null) = if (query.isNullOrEmpty()) "explore" else "explore?query=$query"
    }
    object GymDetails : Screen("gym_details/{gymId}") {
        fun createRoute(gymId: String) = "gym_details/$gymId"
    }
    object CitySelection : Screen("city_selection")
    object Compare : Screen("compare")
    object Favorites : Screen("favorites")
}
