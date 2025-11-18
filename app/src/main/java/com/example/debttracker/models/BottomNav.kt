package com.example.debttracker.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.ui.graphics.vector.ImageVector

open class BottomNavItem (val route: String, val icon: ImageVector, val title: String) {
    data object Records : BottomNavItem("records", Icons.AutoMirrored.Filled.List, "Records")
    data object AddRecord : BottomNavItem("addRecord", Icons.Filled.Add, "Add Record")
    //data object UpdateRecord : BottomNavItem("updateRecord", Icons.Filled.Edit, "Update Record")
    //data object Update_Record : BottomNavItem("updateRecord", Icons.Filled.Edit, "Update Record")
}