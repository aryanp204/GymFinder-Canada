package com.gymfinder.canada.ui.details

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Female
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.HotTub
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Pool
import androidx.compose.material.icons.filled.Shower
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gymfinder.canada.domain.model.Gym
import com.gymfinder.canada.ui.components.PrimaryButton
import com.gymfinder.canada.ui.theme.CardBackground
import com.gymfinder.canada.ui.theme.MainBackground
import com.gymfinder.canada.ui.theme.PrimaryOrange
import com.gymfinder.canada.ui.theme.PrimaryText
import com.gymfinder.canada.ui.theme.SecondaryBackground
import com.gymfinder.canada.ui.theme.SecondaryText
import com.gymfinder.canada.util.CurrencyFormatter
import com.gymfinder.canada.util.ImageResolver
import com.gymfinder.canada.viewmodel.GymDetailsViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GymDetailsScreen(
    gymId: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: GymDetailsViewModel = viewModel()
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(gymId) {
        viewModel.loadGymDetails(gymId)
    }

    if (uiState.isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MainBackground),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = PrimaryOrange)
        }
        return
    }

    val gym = uiState.gym
    if (gym == null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MainBackground)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Gym not found.",
                color = PrimaryText,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            PrimaryButton(text = "Go Back", onClick = onBackClick)
        }
        return
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MainBackground)
            .verticalScroll(rememberScrollState())
    ) {
        // Top App Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
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

            Text(
                text = "Gym details",
                color = PrimaryText,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            IconButton(
                onClick = {
                    Toast.makeText(context, "Favorites feature coming in Part 3!", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(CardBackground)
            ) {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = PrimaryOrange
                )
            }
        }

        // Hero Image
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .padding(horizontal = 20.dp)
                .clip(RoundedCornerShape(20.dp))
        ) {
            Image(
                painter = painterResource(id = ImageResolver.getDrawableResId(gym.imageReference)),
                contentDescription = gym.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Gym Title & Price Header Row
        Column(
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = gym.name,
                    color = PrimaryText,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = CurrencyFormatter.formatMonthlyPrice(gym.monthlyPrice),
                        color = PrimaryOrange,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Black
                    )
                    Text(
                        text = "per month • CAD",
                        color = SecondaryText,
                        fontSize = 11.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Rating & Reviews
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Rating",
                    tint = PrimaryOrange,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "${gym.rating}",
                    color = PrimaryText,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "(${gym.reviewCount} reviews)",
                    color = SecondaryText,
                    fontSize = 13.sp
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Address
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Address",
                    tint = SecondaryText,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = gym.address,
                    color = SecondaryText,
                    fontSize = 13.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Tab Navigation (Overview, Membership, Reviews)
        val tabs = listOf("Overview", "Membership", "Reviews")
        TabRow(
            selectedTabIndex = uiState.selectedTab,
            containerColor = SecondaryBackground,
            contentColor = PrimaryOrange,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    Modifier.tabIndicatorOffset(tabPositions[uiState.selectedTab]),
                    color = PrimaryOrange
                )
            },
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .clip(RoundedCornerShape(12.dp))
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = uiState.selectedTab == index,
                    onClick = { viewModel.selectTab(index) },
                    text = {
                        Text(
                            text = title,
                            color = if (uiState.selectedTab == index) PrimaryOrange else SecondaryText,
                            fontWeight = if (uiState.selectedTab == index) FontWeight.Bold else FontWeight.Medium,
                            fontSize = 14.sp
                        )
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Tab Content
        Column(
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            when (uiState.selectedTab) {
                0 -> {
                    // Overview Tab
                    Text(
                        text = "About the gym",
                        color = PrimaryText,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = gym.description,
                        color = SecondaryText,
                        fontSize = 14.sp,
                        lineHeight = 22.sp
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "Facilities",
                        color = PrimaryText,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        if (gym.isOpen24Hours) FacilityItem(Icons.Default.AccessTime, "24/7 Access")
                        if (gym.hasParking) FacilityItem(Icons.Default.DirectionsCar, "Parking")
                        if (gym.hasPersonalTraining) FacilityItem(Icons.Default.FitnessCenter, "Training")
                        if (gym.hasSwimmingPool) FacilityItem(Icons.Default.Pool, "Pool")
                        if (gym.hasSauna) FacilityItem(Icons.Default.HotTub, "Sauna")
                        if (gym.hasShowers) FacilityItem(Icons.Default.Shower, "Showers")
                        if (gym.hasWiFi) FacilityItem(Icons.Default.Wifi, "Wi-Fi")
                        if (gym.hasGroupClasses) FacilityItem(Icons.Default.Groups, "Classes")
                        if (gym.isWomenOnly) FacilityItem(Icons.Default.Female, "Women Only")
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "Hours & Contact",
                        color = PrimaryText,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Hours: ${gym.openingHours}", color = SecondaryText, fontSize = 14.sp)
                    Text(text = "Phone: ${gym.phone}", color = SecondaryText, fontSize = 14.sp)
                    Text(text = "Email: ${gym.email}", color = SecondaryText, fontSize = 14.sp)
                }

                1 -> {
                    // Membership Tab
                    Text(
                        text = "Membership Plans",
                        color = PrimaryText,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    MembershipCard("Monthly Pass", CurrencyFormatter.formatMonthlyPrice(gym.monthlyPrice), "Billed monthly • No long-term contract")
                    Spacer(modifier = Modifier.height(12.dp))
                    MembershipCard("Annual Membership", CurrencyFormatter.formatMonthlyPrice(gym.annualPrice), "Billed annually • Save 15%")
                    Spacer(modifier = Modifier.height(12.dp))
                    MembershipCard("One-time Joining Fee", CurrencyFormatter.formatMonthlyPrice(gym.joiningFee), "One-time enrollment & card setup fee")
                }

                2 -> {
                    // Reviews Tab
                    Text(
                        text = "Demonstration Reviews",
                        color = PrimaryText,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Sample community feedback for ${gym.name}",
                        color = SecondaryText,
                        fontSize = 13.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    ReviewCard("Alex M.", 5, "Amazing equipment and clean facilities! Staff is super friendly.")
                    Spacer(modifier = Modifier.height(12.dp))
                    ReviewCard("Sarah K.", 4, "Great atmosphere for lifting. Parking is always available.")
                }
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        // Compare Gym Button
        PrimaryButton(
            text = "Compare Gym",
            onClick = {
                Toast.makeText(context, "Gym comparison matrix feature coming in Part 4!", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
fun FacilityItem(icon: ImageVector, label: String) {
    Card(
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Icon(imageVector = icon, contentDescription = label, tint = PrimaryOrange, modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = label, color = SecondaryText, fontSize = 12.sp, fontWeight = FontWeight.Medium)
        }
    }
}

@Composable
fun MembershipCard(title: String, price: String, subtitle: String) {
    Card(
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = title, color = PrimaryText, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = subtitle, color = SecondaryText, fontSize = 12.sp)
            }
            Text(text = price, color = PrimaryOrange, fontSize = 18.sp, fontWeight = FontWeight.Black)
        }
    }
}

@Composable
fun ReviewCard(author: String, rating: Int, comment: String) {
    Card(
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = author, color = PrimaryText, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                Text(text = "★ $rating/5", color = PrimaryOrange, fontSize = 13.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = comment, color = SecondaryText, fontSize = 13.sp)
        }
    }
}
