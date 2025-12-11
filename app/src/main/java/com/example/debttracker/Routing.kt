package com.example.debttracker

import androidx.compose.ui.graphics.vector.ImageVector

open class Screen(
    val icon: ImageVector? = null,
    val route: String,
    val title: String
){
    data object UpdateDebtScreen: Screen(title = "Update Debt", route = "updateDebt")
}