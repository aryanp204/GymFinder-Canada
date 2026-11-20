package com.gymfinder.canada.ui.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Home : Screen("home")
    object Explore : Screen("explore")
    object Compare : Screen("compare")
    object Favorites : Screen("favorites")
}
