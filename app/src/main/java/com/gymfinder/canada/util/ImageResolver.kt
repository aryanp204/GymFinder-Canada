package com.gymfinder.canada.util

import com.gymfinder.canada.R

object ImageResolver {

    fun getDrawableResId(imageReference: String?): Int {
        return when (imageReference) {
            "ic_gym_ironpeak" -> R.drawable.ic_gym_ironpeak
            "ic_gym_urbanfit" -> R.drawable.ic_gym_urbanfit
            "ic_gym_elitecore" -> R.drawable.ic_gym_elitecore
            "ic_gym_northstar" -> R.drawable.ic_gym_northstar
            "ic_promo_athlete" -> R.drawable.ic_promo_athlete
            "ic_splash_bg" -> R.drawable.ic_splash_bg
            else -> R.drawable.ic_gym_ironpeak
        }
    }
}
