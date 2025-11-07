package com.example.debttracker.formatters

import android.icu.text.DecimalFormat
import java.math.BigDecimal

fun moneyFormat(num: Double?): String{
    val format = DecimalFormat("#,###")
    var formattedNumber = format.format(num)
    formattedNumber = "₦$formattedNumber"
    return formattedNumber
}