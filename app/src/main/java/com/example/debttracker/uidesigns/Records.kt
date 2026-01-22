package com.example.debttracker.uidesigns

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.debttracker.models.BottomNavItem
import com.example.debttracker.models.Debt
import com.example.debttracker.models.DebtRecord
import com.example.debttracker.models.DeleteCardsDialog
import com.example.debttracker.viewmodels.RecordsViewmodel

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Records(vm: RecordsViewmodel, navController: NavController) {

    val debtRecords by vm.debtors.collectAsState()
    val alert = remember { mutableStateOf(false) }
    val selectedItems = remember { mutableStateListOf<DebtRecord>() }

    Scaffold(
        topBar = {
            if (selectedItems.size>0) {
                TopAppBar(
                    title = {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("${selectedItems.size} items selected")
                            IconButton(
                                onClick = { alert.value = true }
                            ) {
                                Icon( Icons.Default.Delete, "Delete records")
                            }
                        }
                    }
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F7FA))   // Soft modern background
                .padding(it)
        ) {

            // -------------------------------------------------------------
            // Header
            // -------------------------------------------------------------
            if (selectedItems.size == 0){
                Text(
                    "Your Records",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    ),
                    modifier = Modifier.padding(vertical = 16.dp)
                )
            }

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
                        "CLick the button below to add your first debtor record.",
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

            // Records List
            else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {

                    items(debtRecords) { debtRecord ->
                        RecordCard(
                            vm = vm,
                            debtRecord = debtRecord?: DebtRecord(),
                            navController = navController,
                            selected = selectedItems.contains(debtRecord),
                            selectedItems = selectedItems
                        )
                    }

                    item { Spacer(Modifier.height(10.dp)) }
                }
            }
            if (alert.value){
                val context = LocalContext.current
                DeleteCardsDialog(
                    onDelete = {
                        vm.deleteRecords(selectedItems)
                        selectedItems.clear()
                               },
                    records = selectedItems ,
                    alert = alert,
                    context = context
                )
            }
        }
    }
}



@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun PreviewRecords() {
    Records(viewModel(), rememberNavController())
}
