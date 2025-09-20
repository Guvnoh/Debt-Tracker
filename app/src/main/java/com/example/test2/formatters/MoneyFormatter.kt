package com.example.test2.formatters

import android.icu.text.DecimalFormat
import java.math.BigDecimal

fun moneyFormat(num: BigDecimal?): String{
    val format = DecimalFormat("#,###")
    var formattedNumber = format.format(num)
    formattedNumber = "₦$formattedNumber"
    return formattedNumber
}