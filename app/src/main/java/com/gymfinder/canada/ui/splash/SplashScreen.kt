package com.gymfinder.canada.ui.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gymfinder.canada.R
import com.gymfinder.canada.ui.theme.MainBackground
import com.gymfinder.canada.ui.theme.PrimaryOrange
import com.gymfinder.canada.ui.theme.PrimaryText
import com.gymfinder.canada.ui.theme.SecondaryText

@Composable
fun SplashScreen(
    onSplashFinished: () -> Unit
) {
    val alphaAnim = remember { Animatable(1f) }
    val scaleAnim = remember { Animatable(1f) }

    LaunchedEffect(key1 = true) {
        kotlinx.coroutines.delay(2000L)
        onSplashFinished()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MainBackground)
    ) {
        // Background gym photo / vector backdrop
        Image(
            painter = painterResource(id = R.drawable.ic_splash_bg),
            contentDescription = "Splash Gym Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Gradient Overlay
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xAA0B0F14),
                            Color(0x300B0F14),
                            Color(0xC00B0F14),
                            MainBackground
                        )
                    )
                )
        )

        // Overlay Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 36.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Upper Text
            Text(
                text = "BUILT FOR YOUR NEXT CHAPTER",
                color = SecondaryText,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp,
                modifier = Modifier.padding(top = 16.dp)
            )

            // Center Branding Section
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Dumbbell Logo
                Icon(
                    painter = painterResource(id = R.drawable.ic_dumbbell_logo),
                    contentDescription = "Logo",
                    tint = PrimaryOrange,
                    modifier = Modifier.size(36.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                // GymFinder Title
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(color = PrimaryText, fontWeight = FontWeight.Bold, fontSize = 28.sp)) {
                            append("Gym")
                        }
                        withStyle(style = SpanStyle(color = PrimaryOrange, fontWeight = FontWeight.Bold, fontSize = 28.sp)) {
                            append("Finder")
                        }
                    },
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "CANADA",
                    color = SecondaryText,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 3.sp
                )

                Spacer(modifier = Modifier.height(36.dp))

                // Headline
                Text(
                    text = "Find Your Gym.\nFuel Your Goals.",
                    color = PrimaryText,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Black,
                    lineHeight = 36.sp,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Subhead
                Text(
                    text = "Your next rep starts here.",
                    color = SecondaryText,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Decorative Orange Line
                Box(
                    modifier = Modifier
                        .size(width = 36.dp, height = 3.dp)
                        .background(PrimaryOrange, RoundedCornerShape(2.dp))
                )
            }

            // Bottom Text
            Text(
                text = "DISCOVER • COMPARE • BELONG",
                color = SecondaryText,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.5.sp,
                modifier = Modifier.padding(bottom = 12.dp)
            )
        }
    }
}

@androidx.compose.ui.tooling.preview.Preview(showBackground = true, backgroundColor = 0xFF0B0F14)
@Composable
fun SplashScreenPreview() {
    com.gymfinder.canada.ui.theme.GymFinderTheme {
        SplashScreen(onSplashFinished = {})
    }
}
