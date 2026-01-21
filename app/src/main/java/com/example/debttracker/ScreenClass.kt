package com.example.debttracker

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.ui.graphics.vector.ImageVector

open class Screen(
    val icon: ImageVector? = null,
    val route: String,
    val title: String
){
    data object UpdateDebtScreen: Screen(title = "Update Debt", route = "updateDebt")
    data object AddRecordScreen: Screen(title = "Add Record", route = "addRecord", icon = Icons.Filled.AddCircle )
}