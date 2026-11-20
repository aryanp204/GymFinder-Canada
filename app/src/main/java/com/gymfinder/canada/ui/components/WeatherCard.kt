package com.gymfinder.canada.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gymfinder.canada.ui.theme.CardBackground
import com.gymfinder.canada.ui.theme.PrimaryOrange
import com.gymfinder.canada.ui.theme.PrimaryText
import com.gymfinder.canada.ui.theme.SecondaryText

@Composable
fun WeatherCard(
    temp: String = "18°C",
    condition: String = "Partly cloudy",
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .background(CardBackground, RoundedCornerShape(16.dp))
            .padding(horizontal = 14.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.WbSunny,
            contentDescription = "Weather",
            tint = PrimaryOrange
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Text(
                text = temp,
                color = PrimaryText,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = condition,
                color = SecondaryText,
                fontSize = 11.sp
            )
        }
    }
}
