package com.example.test2.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.test2.uidesigns.AddDebt
import com.example.test2.uidesigns.ClearDebt
import com.example.test2.uidesigns.MainScreen
import com.example.test2.uidesigns.Records

data class NavItem(
    val route: String,
    val label: String,
    val icon: ImageVector
)

@Composable
fun BottomNav(){
    val navcontroller = rememberNavController()
    val items = listOf(
        NavItem("addRecord","Add record", Icons.Default.Add),
        NavItem("records","Records", Icons.Default.List),
        NavItem("updateRecord","Update record", Icons.Default.Edit)
    )
    Scaffold (
        bottomBar = {
            NavigationBar {
                val currentRoute = navcontroller.currentBackStackEntryAsState().value?.destination?.route
                items.forEach {
                    item ->
                    NavigationBarItem(
                        selected = currentRoute == item.route,
                        onClick = {navcontroller.navigate(item.route)},
                        icon = { Icon(item.icon, item.label) },
                        label = { Text(item.label) }
                    )
                }
            }
        }
    ){ innerPadding ->
        NavHost(
            startDestination = "records",
            navController = navcontroller,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("addRecord"){ AddDebt() }
            composable("records") { Records() }
            composable("updateRecord"){ MainScreen() }
        }
    }
}
