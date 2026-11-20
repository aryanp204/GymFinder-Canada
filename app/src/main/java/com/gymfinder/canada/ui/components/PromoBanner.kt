package com.gymfinder.canada.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gymfinder.canada.R
import com.gymfinder.canada.ui.theme.CardBackground
import com.gymfinder.canada.ui.theme.DarkOrangeAccent
import com.gymfinder.canada.ui.theme.PrimaryOrange
import com.gymfinder.canada.ui.theme.PrimaryText
import com.gymfinder.canada.ui.theme.SecondaryText

@Composable
fun PromoBanner(
    onExploreClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(180.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(CardBackground)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1.2f)
                    .padding(20.dp)
            ) {
                // Badge
                Box(
                    modifier = Modifier
                        .background(DarkOrangeAccent, RoundedCornerShape(6.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "YOUR NEXT CHAPTER",
                        color = PrimaryOrange,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "STRONGER\nSTARTS HERE",
                    color = PrimaryText,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black,
                    lineHeight = 24.sp
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Great gyms. Bigger goals.",
                    color = SecondaryText,
                    fontSize = 12.sp
                )

                Spacer(modifier = Modifier.height(14.dp))

                PrimaryButton(
                    text = "Explore Gyms",
                    onClick = onExploreClick,
                    trailingIcon = Icons.AutoMirrored.Filled.ArrowForward
                )
            }

            Image(
                painter = painterResource(id = R.drawable.ic_promo_athlete),
                contentDescription = "Fitness Athlete Photograph",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .weight(0.9f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(topEnd = 20.dp, bottomEnd = 20.dp))
            )
        }
    }
}
