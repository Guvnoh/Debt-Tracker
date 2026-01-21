package com.example.debttracker.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Warning
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.debttracker.Screen

open class BottomNavItem (val route: String, val icon: ImageVector, val title: String) {
    data object Records : BottomNavItem("records", Icons.AutoMirrored.Filled.List, "Records")
    data object AddRecord : BottomNavItem(
        Screen.AddRecordScreen.route,
        Screen.AddRecordScreen.icon?:Icons.Filled.Warning,
        Screen.AddRecordScreen.title)
    //data object UpdateRecord : BottomNavItem("updateRecord", Icons.Filled.Edit, "Update Record")
    //data object Update_Record : BottomNavItem("updateRecord", Icons.Filled.Edit, "Update Record")
}