package com.example.debttracker.formatters

import java.util.Locale

fun capitaliseFirst(text: String): String{
    val formattedText = text.replaceFirstChar { if (it. isLowerCase()) it. titlecase(
        Locale. getDefault()) else it. toString() }
    return formattedText
}

fun textToDisplay(text: String): String{
    val displayText = when(text){
        "Cocacola" -> "Cocacola bottles"
        "Hero12" -> "Hero(x12)"
        "Hero20" -> "Hero(x20)"
        "Hero24" -> "Hero(x24)"
        "Nbl12" -> "Nbl(x12)"
        "Nbl20" -> "Nbl(x20)"
        "Nbl24" -> "Nbl(x24)"
        "Guinness12" -> "Guinness(x12)"
        "Guinness18" -> "Guinness(x18)"
        "Guinness24" -> "Guinness(x24)"
        else -> "error"
    }
    return displayText
}