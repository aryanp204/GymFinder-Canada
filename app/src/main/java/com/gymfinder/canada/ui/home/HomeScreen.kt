package com.gymfinder.canada.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gymfinder.canada.domain.model.City
import com.gymfinder.canada.ui.components.CitySelector
import com.gymfinder.canada.ui.components.GymCard
import com.gymfinder.canada.ui.components.GymLogo
import com.gymfinder.canada.ui.components.PromoBanner
import com.gymfinder.canada.ui.components.SearchField
import com.gymfinder.canada.ui.components.SectionHeading
import com.gymfinder.canada.ui.components.WeatherCard
import com.gymfinder.canada.ui.theme.CardBackground
import com.gymfinder.canada.ui.theme.CardBorder
import com.gymfinder.canada.ui.theme.MainBackground
import com.gymfinder.canada.ui.theme.PrimaryOrange
import com.gymfinder.canada.ui.theme.PrimaryText
import com.gymfinder.canada.ui.theme.SecondaryText
import com.gymfinder.canada.util.CityDataProvider

@Composable
fun HomeScreen(
    selectedCity: City,
    onNavigateToExplore: (String?) -> Unit,
    onCityClick: () -> Unit,
    onGymClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(selectedCity) {
        viewModel.updateSelectedCity(selectedCity)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MainBackground)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        // Header Row: Logo & Notification Icon
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            GymLogo(iconSize = 28.dp)

            IconButton(
                onClick = { /* Notification click placeholder */ },
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(CardBackground)
                    .border(1.dp, CardBorder, CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.NotificationsNone,
                    contentDescription = "Notifications",
                    tint = PrimaryText
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Main Heading
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = PrimaryText, fontWeight = FontWeight.Bold, fontSize = 28.sp)) {
                    append("Find Your\n")
                }
                withStyle(style = SpanStyle(color = PrimaryOrange, fontWeight = FontWeight.Bold, fontSize = 28.sp)) {
                    append("Perfect Gym")
                }
            },
            lineHeight = 34.sp
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Discover a place that moves you.",
            color = SecondaryText,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        // City & Weather Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            CitySelector(
                cityName = selectedCity.displayName,
                onClick = onCityClick,
                modifier = Modifier.weight(1f)
            )
            WeatherCard(
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Search Field
        SearchField(
            query = uiState.searchQuery,
            onQueryChange = viewModel::onSearchQueryChanged
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Promotional Banner
        PromoBanner(
            onExploreClick = { onNavigateToExplore(null) }
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Featured Gyms Section
        SectionHeading(
            title = "Featured gyms",
            actionText = "See all",
            onActionClick = { onNavigateToExplore(null) }
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Horizontal LazyRow of Featured Gym Cards
        LazyRow(
            contentPadding = PaddingValues(bottom = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(uiState.featuredGyms, key = { it.id }) { gym ->
                GymCard(
                    gym = gym,
                    onClick = { onGymClick(gym.id) },
                    onFavoriteClick = { /* Bookmark toggle in Part 3 */ }
                )
            }
        }

        // Bottom Clearance Spacer to ensure no overlap with Bottom Navigation Bar
        Spacer(modifier = Modifier.height(80.dp))
    }
}

@androidx.compose.ui.tooling.preview.Preview(showBackground = true, backgroundColor = 0xFF0B0F14)
@Composable
fun HomeScreenPreview() {
    com.gymfinder.canada.ui.theme.GymFinderTheme {
        HomeScreen(
            selectedCity = CityDataProvider.defaultCity,
            onNavigateToExplore = {},
            onCityClick = {},
            onGymClick = {}
        )
    }
}
