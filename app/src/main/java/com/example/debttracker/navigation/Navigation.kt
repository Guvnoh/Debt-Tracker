package com.example.debttracker.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.debttracker.models.BottomNavItem
import com.example.debttracker.models.SharedViewModel
import com.example.debttracker.uidesigns.AddDebt
import com.example.debttracker.uidesigns.ClearDebt
import com.example.debttracker.uidesigns.Records

//data class NavItem(
//    val route: String,
//    val label: String,
//    val icon: ImageVector
//)

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BottomNav() {
    val navController = rememberNavController()
    val items = listOf(
        BottomNavItem.Add_Record,
        BottomNavItem.Records,
        BottomNavItem.Update_Record,
    )
    val viewModel: SharedViewModel = viewModel()

    Scaffold(
        bottomBar = {
            NavigationBar(
                tonalElevation = 4.dp,
                containerColor = MaterialTheme.colorScheme.surface
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                items.forEach { item ->
                    val selected = currentRoute == item.route
                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.title,
                                tint = if (selected) {
                                    MaterialTheme.colorScheme.primary
                                } else {
                                    MaterialTheme.colorScheme.onSurfaceVariant
                                }
                            )
                        },
                        label = {
                            Text(
                                text = item.title,
                                style = MaterialTheme.typography.labelSmall,
                                color = if (selected) {
                                    MaterialTheme.colorScheme.primary
                                } else {
                                    MaterialTheme.colorScheme.onSurfaceVariant
                                }
                            )
                        },
                        alwaysShowLabel = true
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            startDestination = BottomNavItem.Records.route,
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Add_Record.route) {
                AddDebt(navController)
            }
            composable(BottomNavItem.Records.route) {
                Records(vm = viewModel, navController = navController)
            }
            composable(BottomNavItem.Update_Record.route) {
                Records(vm = viewModel, navController = navController)
            }
            composable("ClearDebt") {
                ClearDebt(viewModel.selectedRecord)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun ShowBottomBar(){
    BottomNav()
}
