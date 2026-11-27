package com.gymfinder.canada.ui.explore

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gymfinder.canada.domain.model.City
import com.gymfinder.canada.domain.model.Gym
import com.gymfinder.canada.domain.model.GymSortOption
import com.gymfinder.canada.ui.components.PrimaryButton
import com.gymfinder.canada.ui.components.SearchField
import com.gymfinder.canada.ui.theme.CardBackground
import com.gymfinder.canada.ui.theme.CardBorder
import com.gymfinder.canada.ui.theme.DarkOrangeAccent
import com.gymfinder.canada.ui.theme.MainBackground
import com.gymfinder.canada.ui.theme.PrimaryOrange
import com.gymfinder.canada.ui.theme.PrimaryText
import com.gymfinder.canada.ui.theme.SecondaryBackground
import com.gymfinder.canada.ui.theme.SecondaryText
import com.gymfinder.canada.util.CurrencyFormatter
import com.gymfinder.canada.util.ImageResolver
import com.gymfinder.canada.viewmodel.ExploreViewModel

@Composable
fun ExploreScreen(
    selectedCity: City,
    initialSearchQuery: String?,
    onGymClick: (String) -> Unit,
    onCityClick: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ExploreViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var isSortDropdownExpanded by remember { mutableStateOf(false) }

    LaunchedEffect(selectedCity) {
        viewModel.updateSelectedCity(selectedCity)
    }

    LaunchedEffect(initialSearchQuery) {
        if (!initialSearchQuery.isNullOrEmpty()) {
            viewModel.onQueryChanged(initialSearchQuery)
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MainBackground)
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        // Dynamic City Header Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(CardBackground)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = PrimaryText
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Gyms in ${selectedCity.name}",
                    color = PrimaryText,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Find your fit in the city.",
                    color = SecondaryText,
                    fontSize = 12.sp
                )
            }
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(CardBackground)
                    .clickable(onClick = onCityClick)
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Text(
                    text = selectedCity.name,
                    color = PrimaryOrange,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Search Field
        SearchField(
            query = uiState.searchQuery,
            onQueryChange = viewModel::onQueryChanged,
            placeholder = "Search gyms, facilities, or locations"
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Horizontally Scrollable Filter Chips
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(end = 12.dp)
        ) {
            item {
                val isAllSelected = !uiState.filter.isActive
                FilterChip(
                    selected = isAllSelected,
                    onClick = { viewModel.clearFilters() },
                    label = { Text("All gyms") },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = PrimaryOrange,
                        selectedLabelColor = Color.White,
                        containerColor = CardBackground,
                        labelColor = SecondaryText
                    )
                )
            }
            item {
                FilterChip(
                    selected = uiState.filter.isOpen24Hours,
                    onClick = { viewModel.toggleFilter247() },
                    label = { Text("24/7") },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = PrimaryOrange,
                        selectedLabelColor = Color.White,
                        containerColor = CardBackground,
                        labelColor = SecondaryText
                    )
                )
            }
            item {
                FilterChip(
                    selected = uiState.filter.hasSwimmingPool,
                    onClick = { viewModel.toggleFilterPool() },
                    label = { Text("Pool") },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = PrimaryOrange,
                        selectedLabelColor = Color.White,
                        containerColor = CardBackground,
                        labelColor = SecondaryText
                    )
                )
            }
            item {
                FilterChip(
                    selected = uiState.filter.hasPersonalTraining,
                    onClick = { viewModel.toggleFilterTraining() },
                    label = { Text("Training") },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = PrimaryOrange,
                        selectedLabelColor = Color.White,
                        containerColor = CardBackground,
                        labelColor = SecondaryText
                    )
                )
            }
            item {
                FilterChip(
                    selected = uiState.filter.isBudget,
                    onClick = { viewModel.toggleFilterBudget() },
                    label = { Text("Budget (≤$40)") },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = PrimaryOrange,
                        selectedLabelColor = Color.White,
                        containerColor = CardBackground,
                        labelColor = SecondaryText
                    )
                )
            }
            item {
                FilterChip(
                    selected = uiState.filter.hasParking,
                    onClick = { viewModel.toggleFilterParking() },
                    label = { Text("Parking") },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = PrimaryOrange,
                        selectedLabelColor = Color.White,
                        containerColor = CardBackground,
                        labelColor = SecondaryText
                    )
                )
            }
            item {
                FilterChip(
                    selected = uiState.filter.hasSauna,
                    onClick = { viewModel.toggleFilterSauna() },
                    label = { Text("Sauna") },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = PrimaryOrange,
                        selectedLabelColor = Color.White,
                        containerColor = CardBackground,
                        labelColor = SecondaryText
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Results Count & Sorting Bar Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${uiState.gyms.size} gyms • ${selectedCity.name}",
                color = SecondaryText,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )

            // Sort Dropdown Trigger
            Box {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .clickable { isSortDropdownExpanded = true }
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = uiState.sortOption.label,
                        color = PrimaryText,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Sort Options",
                        tint = PrimaryOrange
                    )
                }

                DropdownMenu(
                    expanded = isSortDropdownExpanded,
                    onDismissRequest = { isSortDropdownExpanded = false },
                    modifier = Modifier.background(SecondaryBackground)
                ) {
                    GymSortOption.entries.forEach { option ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = option.label,
                                    color = if (option == uiState.sortOption) PrimaryOrange else PrimaryText
                                )
                            },
                            onClick = {
                                viewModel.setSortOption(option)
                                isSortDropdownExpanded = false
                            }
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        if (uiState.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = PrimaryOrange)
            }
        } else if (uiState.gyms.isEmpty()) {
            // Empty State
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(72.dp)
                        .clip(CircleShape)
                        .background(CardBackground),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "No Results",
                        tint = PrimaryOrange,
                        modifier = Modifier.size(36.dp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "No gyms found",
                    color = PrimaryText,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Try adjusting your search query or facility filters.",
                    color = SecondaryText,
                    fontSize = 13.sp,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                PrimaryButton(
                    text = "Clear Filters",
                    onClick = { viewModel.clearFilters() }
                )
            }
        } else {
            // LazyColumn of Compact Horizontal Gym Cards (Figma Screen 3 layout)
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(14.dp),
                contentPadding = PaddingValues(bottom = 80.dp)
            ) {
                items(uiState.gyms, key = { it.id }) { gym ->
                    ExploreGymCard(
                        gym = gym,
                        onClick = { onGymClick(gym.id) }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ExploreGymCard(
    gym: Gym,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Gym Image
            Image(
                painter = painterResource(id = ImageResolver.getDrawableResId(gym.imageReference)),
                contentDescription = gym.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(width = 110.dp, height = 100.dp)
                    .clip(RoundedCornerShape(12.dp))
            )

            Spacer(modifier = Modifier.width(14.dp))

            // Details
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = gym.name,
                        color = PrimaryText,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = PrimaryOrange,
                        modifier = Modifier.size(18.dp)
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                // Rating Line
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Rating",
                        tint = PrimaryOrange,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${gym.rating}",
                        color = PrimaryText,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "(${gym.reviewCount})",
                        color = SecondaryText,
                        fontSize = 12.sp
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                // Price
                Row(
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = CurrencyFormatter.formatMonthlyPrice(gym.monthlyPrice),
                        color = PrimaryOrange,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = " /month",
                        color = SecondaryText,
                        fontSize = 11.sp,
                        modifier = Modifier.padding(bottom = 1.dp)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Facility Chips Row
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    if (gym.isOpen24Hours) CompactChip("24/7")
                    if (gym.hasParking) CompactChip("Parking")
                    if (gym.hasSwimmingPool) CompactChip("Pool")
                    if (gym.hasSauna) CompactChip("Sauna")
                    if (gym.hasPersonalTraining) CompactChip("Training")
                }
            }
        }
    }
}

@Composable
fun CompactChip(label: String) {
    Box(
        modifier = Modifier
            .background(SecondaryBackground, RoundedCornerShape(6.dp))
            .padding(horizontal = 6.dp, vertical = 2.dp)
    ) {
        Text(
            text = label,
            color = SecondaryText,
            fontSize = 10.sp,
            fontWeight = FontWeight.Medium
        )
    }
}
