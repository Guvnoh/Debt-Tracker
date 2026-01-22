package com.example.debttracker.uidesigns

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
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

        //=======================
        //   PAGE HEADER
        //=======================
        Text(
            text = "Add Debt Record",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.primary
        )

        //=======================
        //   DEBTOR INFO CARD
        //=======================
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
                    singleLine = true
                )

                OutlinedTextField(
                    value = cash,
                    onValueChange = { viewModel.setCashAmount(it) },
                    label = { Text("Cash Amount") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
        }


        //   BOTTLE SECTION CARD
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
                    "Bottles",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                viewModel.bottleRows.collectAsState().value.forEachIndexed { index, _ ->
                    AddItemRow(index, viewModel, RowType.Bottles)
                }


            }
        }

        //Empties section
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
                    "Empties",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                viewModel.emptiesRows.collectAsState().value.forEachIndexed { index, _ ->
                    AddItemRow(index, viewModel, RowType.Empties)
                }


            }
        }

        //Plastics section
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
                    "Plastics",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                viewModel.plasticRows.collectAsState().value.forEachIndexed { index, _ ->
                    AddItemRow(index, viewModel, RowType.Plastics)
                }


            }
        }

        //   SUBMIT BUTTON
        Button(
            onClick = {
                val debt = viewModel.createRecord(context)
                if (debt.itemsOwed!=null) viewModel.saveRecord(debt, context)
                navController.navigate(Screen.Records.route)
                viewModel.clearAll()
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



