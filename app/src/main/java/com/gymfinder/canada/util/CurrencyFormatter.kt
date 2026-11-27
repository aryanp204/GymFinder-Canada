package com.gymfinder.canada.util

import java.util.Locale

object CurrencyFormatter {

    fun formatMonthlyPrice(price: Double): String {
        return String.format(Locale.CANADA, "$%.2f", price)
    }

    fun formatPriceWithSuffix(price: Double): String {
        return "${formatMonthlyPrice(price)} /mo"
    }
}
