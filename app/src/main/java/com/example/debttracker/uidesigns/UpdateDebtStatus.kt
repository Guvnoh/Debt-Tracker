package com.example.debttracker.uidesigns

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.debttracker.deactivateRecord
import com.example.debttracker.formatters.TimeAndDate
import com.example.debttracker.formatters.halfAndQuarter
import com.example.debttracker.formatters.moneyFormat
import com.example.debttracker.models.BottleType
import com.example.debttracker.models.CustomAlertDialog
import com.example.debttracker.models.Debt
import com.example.debttracker.models.DebtType
import com.example.debttracker.models.Debtor
import com.example.debttracker.models.Options
import com.example.debttracker.models.DialogKey
import com.example.debttracker.models.SharedViewModel
import com.example.debttracker.reactivateRecord

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun ClearDebtDemo(){
    val new = Debt(
            timeAdded = TimeAndDate().GetTimeString(),
            dateAdded = TimeAndDate().GetDateString(),
            itemsOwed = mutableMapOf(
                DebtType.Cash.name to 6300.0
            )
        )
    val debtor = Debtor(name = "Mama Ejima", debt = new)
    ClearDebt(debtor = debtor, navController = rememberNavController())
}
@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ClearDebt(
    debtor: Debtor?,
    navController: NavController,
) {
    var deleteRecordItemDialog by remember { mutableStateOf(false) }
    var deleteEntireRecordDialog by remember { mutableStateOf(false) }
    var debtToDelete by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.height(120.dp),
                title = {
                    Row (
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Column(
                        ) {
                            Text(
                                "Debt Details",
                                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                            )
                            debtor?.let {
                                Text(
                                    text = it.name ?: "Unknown",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Text(
                                    text = "Recorded: ${it.debt?.dateAdded}, ${it.debt?.timeAdded}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color.Gray,
                                )
                            }
                        }
                        Options(
                            setInactive = {deactivateRecord(debtor?:Debtor()) },
                            setActive = { reactivateRecord(debtor?:Debtor()) },
                            onDelete = {
                                deleteEntireRecordDialog = true
                            },
                            debtor = debtor?:Debtor()
                        )
                        if (deleteEntireRecordDialog) {
                            debtor?.let {
                                CustomAlertDialog(
                                    debtor=it,
                                    debt=debtToDelete,
                                    navController=navController,
                                    caseKey = DialogKey.topAppBar
                                ) {
                                    deleteEntireRecordDialog = false
                                }
                            }
                        }

                    }
                }
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // --------------------------------------------------------
            // No debt record
            // --------------------------------------------------------
            if (debtor == null || debtor.debt?.itemsOwed.isNullOrEmpty()) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "No debt record found.",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.error
                    )
                }
                return@Column
            }

            // --------------------------------------------------------
            // Debt items
            // --------------------------------------------------------
            fun getBottleName(name: String): String{
                var bottleName = name.replace("_", " ")
                val type = bottleName.takeLast(2).toIntOrNull()?:""
                if (type!=""){
                    bottleName = "${bottleName.dropLast(2)}(x$type)"
                }
                return bottleName
            }
            val bottleTypeList = BottleType.entries.toList().map { it.name }
            debtor.debt?.itemsOwed?.forEach { (type, amount) ->
                val bottleName = getBottleName(type)

                val label = when (type) {
                    DebtType.Cash.name -> "Cash Owed"
                    DebtType.Bottle.name -> "Bottles Owed"
                    DebtType.Empty.name -> "Empties Balance"
                    DebtType.Fulls.name -> "Fulls Owed"
                    DebtType.FullBottle.name -> "Full Bottles Owed"
                    DebtType.Plastic.name -> "Plastics Owed"
                    else -> if (bottleTypeList.contains(type)) "$bottleName bottles owed" else "$type owed"
                }

                val displayAmount = if (type == DebtType.Cash.name) moneyFormat(amount)
                else halfAndQuarter(amount)

                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(4.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Column(
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                text = label,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Text(
                                text = displayAmount,
                                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
                            )
                        }

                        // ------------------------------
                        // Actions
                        // ------------------------------
                        Options(
                            setInactive = { deactivateRecord(debtor) },
                            setActive = { reactivateRecord(debtor) },
                            onDelete = {
                                deleteRecordItemDialog = true
                                debtToDelete = type
                                       },
                            debtor = debtor
                        )
                    }
                }
            }
        }

        // --------------------------------------------------------
        // Delete Confirmation Dialog
        // --------------------------------------------------------
        if (deleteRecordItemDialog) {
            debtor?.let {
                CustomAlertDialog(
                    debtor=it,
                    debt=debtToDelete,
                    navController=navController,
                    caseKey = DialogKey.debtCard
                ) {
                    deleteRecordItemDialog = false
                }
            }
        }
    }
}
