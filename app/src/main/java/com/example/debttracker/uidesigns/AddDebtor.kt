package com.example.debttracker.uidesigns

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.debttracker.Screen
import com.example.debttracker.models.AddItemRow
import com.example.debttracker.models.RowType
import com.example.debttracker.viewmodels.AddDebtorViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddDebt(navController: NavController, viewModel: AddDebtorViewModel) {

    val name by viewModel.customerName
    val cash by viewModel.cash

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Page header with Clear All button
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Add Debt Record",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary
            )
            OutlinedButton(
                onClick = { viewModel.clearAll() },
                shape = RoundedCornerShape(8.dp)
            ) {
                Icon(
                    Icons.Default.Clear,
                    contentDescription = "Clear all",
                    modifier = Modifier.size(18.dp)
                )
                Spacer(Modifier.width(4.dp))
                Text("Clear All", style = MaterialTheme.typography.labelSmall)
            }
        }

        // Debtor Info Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(4.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                Text(
                    "Debtor Information",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                OutlinedTextField(
                    value = name,
                    onValueChange = { viewModel.setCustomerName(it) },
                    label = { Text("Debtor Name") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    isError = false
                )

                OutlinedTextField(
                    value = cash,
                    onValueChange = { viewModel.setCashAmount(it) },
                    label = { Text("Cash Amount") },
                    prefix = { Text("\u20A6 ") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
        }

        // Bottle Section Card
        SectionCard(title = "Bottles", icon = "\uD83C\uDF7A") {
            viewModel.bottleRows.collectAsState().value.forEachIndexed { index, _ ->
                AddItemRow(index, viewModel, RowType.Bottles)
            }
        }

        // Empties Section
        SectionCard(title = "Empties", icon = "\uD83E\uDEE7") {
            viewModel.emptiesRows.collectAsState().value.forEachIndexed { index, _ ->
                AddItemRow(index, viewModel, RowType.Empties)
            }
        }

        // Plastics Section
        SectionCard(title = "Plastics", icon = "\uD83E\uDEF9") {
            viewModel.plasticRows.collectAsState().value.forEachIndexed { index, _ ->
                AddItemRow(index, viewModel, RowType.Plastics)
            }
        }

        // Submit Button
        Button(
            onClick = {
                val debt = viewModel.createRecord(context)
                if (!debt.itemsOwed.isNullOrEmpty()) {
                    viewModel.saveRecord(debt, context)
                    navController.navigate(Screen.Records.route)
                    viewModel.clearAll()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(14.dp)
        ) {
            Text("Add to Records", fontSize = 17.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
private fun SectionCard(
    title: String,
    icon: String,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(icon, fontSize = 20.sp)
                Spacer(Modifier.width(8.dp))
                Text(
                    title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
            content()
        }
    }
}
