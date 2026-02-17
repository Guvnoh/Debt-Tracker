package com.example.debttracker.uidesigns

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
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
import com.example.debttracker.ui.theme.ActiveGreen
import com.example.debttracker.ui.theme.InactiveRed
import com.example.debttracker.viewmodels.RecordsViewmodel
import java.text.NumberFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Records(vm: RecordsViewmodel, navController: NavController) {

    val debtRecords by vm.filteredDebtors.collectAsState()
    val searchQuery by vm.searchQuery.collectAsState()
    val totalRecords by vm.totalRecords.collectAsState()
    val activeCount by vm.activeCount.collectAsState()
    val inactiveCount by vm.inactiveCount.collectAsState()
    val totalCashOwed by vm.totalCashOwed.collectAsState()

    val alert = remember { mutableStateOf(false) }
    val selectedItems = remember { mutableStateListOf<DebtRecord>() }

    Scaffold(
        topBar = {
            if (selectedItems.size > 0) {
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
                                Icon(Icons.Default.Delete, "Delete records")
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
                .background(MaterialTheme.colorScheme.background)
                .padding(it)
        ) {

            // Header
            if (selectedItems.size == 0) {
                Text(
                    "Your Records",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    ),
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
                )
            }

            // Empty State
            if (totalRecords == 0 && searchQuery.isBlank()) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

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
                        "Click the button below to add your first debtor record.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
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

            // Records List with search + summary
            else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {

                    // Search Bar
                    item {
                        OutlinedTextField(
                            value = searchQuery,
                            onValueChange = { query -> vm.updateSearchQuery(query) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 4.dp),
                            placeholder = { Text("Search by name...") },
                            leadingIcon = {
                                Icon(Icons.Default.Search, contentDescription = "Search")
                            },
                            trailingIcon = {
                                if (searchQuery.isNotEmpty()) {
                                    IconButton(onClick = { vm.updateSearchQuery("") }) {
                                        Icon(Icons.Default.Close, contentDescription = "Clear search")
                                    }
                                }
                            },
                            singleLine = true,
                            shape = RoundedCornerShape(12.dp)
                        )
                    }

                    // Summary Stats Card
                    item {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 4.dp, vertical = 4.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer
                            )
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(
                                    "Summary",
                                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    SummaryItem("Total", "$totalRecords", MaterialTheme.colorScheme.onPrimaryContainer)
                                    SummaryItem("Active", "$activeCount", ActiveGreen)
                                    SummaryItem("Inactive", "$inactiveCount", InactiveRed)
                                }
                                if (totalCashOwed > 0) {
                                    val formatted = NumberFormat.getNumberInstance(Locale("en", "NG"))
                                        .format(totalCashOwed)
                                    Text(
                                        "Total Cash Owed: \u20A6$formatted",
                                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                                        color = MaterialTheme.colorScheme.onPrimaryContainer
                                    )
                                }
                            }
                        }
                    }

                    // Record items
                    items(debtRecords, key = { it?.id ?: it.hashCode() }) { debtRecord ->
                        RecordCard(
                            vm = vm,
                            debtRecord = debtRecord ?: DebtRecord(),
                            navController = navController,
                            selected = selectedItems.contains(debtRecord),
                            selectedItems = selectedItems
                        )
                    }

                    // Empty search result
                    if (debtRecords.isEmpty() && searchQuery.isNotBlank()) {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(32.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    "No records matching \"$searchQuery\"",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }

                    item { Spacer(Modifier.height(10.dp)) }
                }
            }
            if (alert.value) {
                val context = LocalContext.current
                DeleteCardsDialog(
                    onDelete = {
                        vm.deleteRecords(selectedItems)
                        selectedItems.clear()
                    },
                    records = selectedItems,
                    alert = alert,
                    context = context
                )
            }
        }
    }
}

@Composable
private fun SummaryItem(label: String, value: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            value,
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            color = color
        )
        Text(
            label,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun PreviewRecords() {
    Records(viewModel(), rememberNavController())
}
