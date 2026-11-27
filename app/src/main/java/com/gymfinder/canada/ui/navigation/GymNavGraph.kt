package com.gymfinder.canada.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.gymfinder.canada.ui.city.CitySelectionScreen
import com.gymfinder.canada.ui.compare.ComparePlaceholderScreen
import com.gymfinder.canada.ui.components.GymBottomNavigation
import com.gymfinder.canada.ui.details.GymDetailsScreen
import com.gymfinder.canada.ui.explore.ExploreScreen
import com.gymfinder.canada.ui.favorites.FavoritesPlaceholderScreen
import com.gymfinder.canada.ui.home.HomeScreen
import com.gymfinder.canada.ui.splash.SplashScreen
import com.gymfinder.canada.viewmodel.SharedCityViewModel

@Composable
fun GymAppRoot(
    navController: NavHostController = rememberNavController(),
    sharedCityViewModel: SharedCityViewModel = viewModel()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val selectedCity by sharedCityViewModel.selectedCity.collectAsState()

    val hideBottomBarRoutes = listOf(
        Screen.Splash.route,
        Screen.GymDetails.route,
        Screen.CitySelection.route
    )
    val showBottomBar = currentRoute != null && !hideBottomBarRoutes.contains(currentRoute)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (showBottomBar) {
                GymBottomNavigation(
                    currentRoute = currentRoute,
                    onNavigate = { targetRoute ->
                        val destinationRoute = if (targetRoute == Screen.Explore.route) Screen.Explore.createRoute(null) else targetRoute
                        navController.navigate(destinationRoute) {
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
                    selectedCity = selectedCity,
                    onNavigateToExplore = { searchQuery ->
                        val route = Screen.Explore.createRoute(searchQuery)
                        navController.navigate(route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    onCityClick = {
                        navController.navigate(Screen.CitySelection.route)
                    },
                    onGymClick = { gymId ->
                        navController.navigate(Screen.GymDetails.createRoute(gymId))
                    }
                )
            }

            composable(
                route = Screen.Explore.route,
                arguments = listOf(
                    navArgument("query") {
                        type = NavType.StringType
                        nullable = true
                        defaultValue = null
                    }
                )
            ) { backStackEntry ->
                val rawQuery = backStackEntry.arguments?.getString("query")
                val queryArg = if (rawQuery == "{query}") null else rawQuery
                ExploreScreen(
                    selectedCity = selectedCity,
                    initialSearchQuery = queryArg,
                    onGymClick = { gymId ->
                        navController.navigate(Screen.GymDetails.createRoute(gymId))
                    },
                    onCityClick = {
                        navController.navigate(Screen.CitySelection.route)
                    },
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }

            composable(
                route = Screen.GymDetails.route,
                arguments = listOf(
                    navArgument("gymId") {
                        type = NavType.StringType
                    }
                )
            ) { backStackEntry ->
                val gymId = backStackEntry.arguments?.getString("gymId") ?: ""
                GymDetailsScreen(
                    gymId = gymId,
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }

            composable(Screen.CitySelection.route) {
                CitySelectionScreen(
                    currentSelectedCity = selectedCity,
                    onCitySelected = { newCity ->
                        sharedCityViewModel.selectCity(newCity)
                        navController.popBackStack()
                    },
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
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
