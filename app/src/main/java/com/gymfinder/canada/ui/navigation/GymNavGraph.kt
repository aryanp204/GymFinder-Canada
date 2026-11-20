package com.gymfinder.canada.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.gymfinder.canada.ui.compare.ComparePlaceholderScreen
import com.gymfinder.canada.ui.components.GymBottomNavigation
import com.gymfinder.canada.ui.explore.ExplorePlaceholderScreen
import com.gymfinder.canada.ui.favorites.FavoritesPlaceholderScreen
import com.gymfinder.canada.ui.home.HomeScreen
import com.gymfinder.canada.ui.splash.SplashScreen

@Composable
fun GymAppRoot(
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val isSplash = currentRoute == Screen.Splash.route

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (!isSplash && currentRoute != null) {
                GymBottomNavigation(
                    currentRoute = currentRoute,
                    onNavigate = { targetRoute ->
                        navController.navigate(targetRoute) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Splash.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Splash.route) {
                SplashScreen(
                    onSplashFinished = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Splash.route) {
                                inclusive = true
                            }
                        }
                    }
                )
            }

            composable(Screen.Home.route) {
                HomeScreen(
                    onNavigateToExplore = {
                        navController.navigate(Screen.Explore.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }

            composable(Screen.Explore.route) {
                ExplorePlaceholderScreen()
            }

            composable(Screen.Compare.route) {
                ComparePlaceholderScreen()
            }

            composable(Screen.Favorites.route) {
                FavoritesPlaceholderScreen()
            }
        }
    }
}
