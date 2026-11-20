package com.gymfinder.canada.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gymfinder.canada.R
import com.gymfinder.canada.ui.theme.PrimaryOrange
import com.gymfinder.canada.ui.theme.PrimaryText
import com.gymfinder.canada.ui.theme.SecondaryText

@Composable
fun GymLogo(
    modifier: Modifier = Modifier,
    iconSize: Dp = 24.dp,
    showSubtext: Boolean = true
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_dumbbell_logo),
            contentDescription = "GymFinder Logo",
            tint = PrimaryOrange,
            modifier = Modifier.size(iconSize)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = PrimaryText, fontWeight = FontWeight.Bold, fontSize = 20.sp)) {
                        append("Gym")
                    }
                    withStyle(style = SpanStyle(color = PrimaryOrange, fontWeight = FontWeight.Bold, fontSize = 20.sp)) {
                        append("Finder")
                    }
                }
            )

            if (showSubtext) {
                Text(
                    text = "CANADA",
                    color = SecondaryText,
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp
                )
            }
        }
    }
}
