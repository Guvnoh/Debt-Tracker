package com.example.debttracker.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Warning
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.debttracker.Screen

open class BottomNavItem (
    val route: String,
    val icon: ImageVector? = Icons.Filled.Warning,
    val title: String
) {
    data object Records : BottomNavItem(
        Screen.Records.route,
        Screen.Records.icon,
        Screen.Records.title
    )
    data object AddRecord : BottomNavItem(
        Screen.AddRecordScreen.route,
        Screen.AddRecordScreen.icon,
        Screen.AddRecordScreen.title
    )
    //data object UpdateRecord : BottomNavItem("updateRecord", Icons.Filled.Edit, "Update Record")
    //data object Update_Record : BottomNavItem("updateRecord", Icons.Filled.Edit, "Update Record")
}