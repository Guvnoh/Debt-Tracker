//package com.example.debttracker.uidesigns
//
//import android.os.Build
//import androidx.annotation.RequiresApi
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material3.Button
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.navigation.NavController
//import androidx.navigation.compose.rememberNavController
//import com.example.debttracker.models.BottomNavItem
//import com.example.debttracker.models.SharedViewModel
//
//@RequiresApi(Build.VERSION_CODES.O)
//@Composable
//fun Records(vm: SharedViewModel, navController: NavController){
//    val debtRecords by vm.debtors.collectAsState()
//
//    if (debtRecords.isEmpty()){
//        Column (
//            Modifier.fillMaxSize(),
//            verticalArrangement = Arrangement.SpaceBetween,
//            horizontalAlignment = Alignment.CenterHorizontally){
//            Text(
//                text = "No debt recorded yet!",
//                style = MaterialTheme.typography.headlineLarge,
//                color = MaterialTheme.colorScheme.primary
//            )
//            Button(onClick = {
//                navController.navigate(BottomNavItem.AddRecord.route) {
//                    launchSingleTop = true
//                }}) {
//                Text("Add new record")
//            }
//        }
//
//    }else{
//        LazyColumn(
//            modifier = Modifier
//                .background(Color.White)
//        ) {
//
//            items(debtRecords){
//                    debtorMap ->
//                DebtCard(vm = vm, debtor = debtorMap, navC = navController)
//            }
//        }
//    }
//}
//
//
//
//
//@RequiresApi(Build.VERSION_CODES.O)
//@Preview
//@Composable
//fun ShowRecords(){
//    Records(vm = viewModel(), navController = rememberNavController())
//}
package com.example.debttracker.uidesigns

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.example.debttracker.Screen
import com.example.debttracker.models.BottomNavItem
import com.example.debttracker.models.Debtor
import com.example.debttracker.models.SharedViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Records(vm: SharedViewModel, navController: NavController) {

    val debtRecords by vm.debtors.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F7FA))   // Soft modern background
            .padding(horizontal = 16.dp)
    ) {

        // -------------------------------------------------------------
        // Header
        // -------------------------------------------------------------
        Text(
            "Your Records",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier.padding(vertical = 16.dp)
        )

        // -------------------------------------------------------------
        // Empty State Layout
        // -------------------------------------------------------------
        if (debtRecords.isEmpty()) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // Optional: Place any illustration you want here
                // (or remove Image block entirely if not needed)
                Image(
                    painter = painterResource(id = android.R.drawable.ic_menu_info_details),
                    contentDescription = "Empty",
                    modifier = Modifier.size(120.dp),
                    contentScale = ContentScale.Fit
                )

                Spacer(Modifier.height(24.dp))

                Text(
                    "No Records Yet",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(Modifier.height(6.dp))

                Text(
                    "Start by adding your first debtor record.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )

                Spacer(Modifier.height(30.dp))

                Button(
                    onClick = {
                        navController.navigate(BottomNavItem.AddRecord.route) {
                            launchSingleTop = true
                        }
                    },
                    modifier = Modifier.padding(12.dp)
                ) {
                    Text("Add New Record")
                }
            }
        }

        // -------------------------------------------------------------
        // Records List
        // -------------------------------------------------------------
        else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {

                items(debtRecords) { debtor ->
                    RecordCard(
                        vm = vm,
                        debtor = debtor?: Debtor(),
                        navController = navController
                    )
                }

                item { Spacer(Modifier.height(10.dp)) }
            }
        }
    }
}


// ================================================================
// Modern Card Layout for each record
// ================================================================
@Composable
fun RecordCard(
    vm: SharedViewModel,
    debtor: Debtor,
    navController: NavController
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Text(
                text = debtor.name.toString(),
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text = "Added: ${debtor.debt?.dateAdded ?: "N/A"}",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )

            Spacer(Modifier.height(10.dp))

            Button(
                onClick = {
                    // Navigate to a detailed record page
                   // navController.navigate("record_details/${debtor.id}")
                    vm.selectedRecord = debtor
                    navController.navigate(Screen.UpdateDebtScreen.route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("View")
            }
        }
    }
}


// Preview
@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun PreviewRecords() {
    Records(viewModel(), rememberNavController())
}
