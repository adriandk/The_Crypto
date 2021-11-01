package com.adrian.thecrypto.core.utils

import java.text.NumberFormat
import java.util.*

object Formatter {

    fun getPrice(price: Double): String? {
        return NumberFormat.getCurrencyInstance(Locale.US).format(price)
    }

    fun getSupply(supply: Double): String? {
        return NumberFormat.getNumberInstance(Locale.US).format(supply)
    }
}