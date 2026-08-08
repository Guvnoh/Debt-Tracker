package com.example.debttracker.formatters

import android.icu.text.DecimalFormat
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

fun moneyFormat(num: Double?): String{
    val format = DecimalFormat("#,###")
    var formattedNumber = format.format(num)
    formattedNumber = "₦$formattedNumber"
    return formattedNumber
}

fun formatWithCommas(input: String): String {
    val digits = input.filter { it.isDigit() }
    if (digits.isEmpty()) return ""
    val number = digits.toLongOrNull() ?: return digits
    val format = DecimalFormat("#,###")
    return format.format(number)
}

class CashVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val digits = text.text
        if (digits.isEmpty()) return TransformedText(text, OffsetMapping.Identity)

        val number = digits.toLongOrNull()
            ?: return TransformedText(text, OffsetMapping.Identity)
        val formatted = DecimalFormat("#,###").format(number)

        return TransformedText(
            AnnotatedString(formatted),
            object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    var digitsSeen = 0
                    for (i in formatted.indices) {
                        if (digitsSeen == offset) return i
                        if (formatted[i] != ',') digitsSeen++
                    }
                    return formatted.length
                }

                override fun transformedToOriginal(offset: Int): Int {
                    var count = 0
                    for (i in 0 until offset.coerceAtMost(formatted.length)) {
                        if (formatted[i] != ',') count++
                    }
                    return count
                }
            }
        )
    }
}