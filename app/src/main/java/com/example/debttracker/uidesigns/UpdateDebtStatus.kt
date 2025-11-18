package com.example.debttracker.uidesigns

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.debttracker.deactivateRecord
import com.example.debttracker.formatters.TimeAndDate
import com.example.debttracker.formatters.halfAndQuarter
import com.example.debttracker.formatters.moneyFormat
import com.example.debttracker.models.CustomAlertDialog
import com.example.debttracker.models.Debt
import com.example.debttracker.models.DebtType
import com.example.debttracker.models.Debtor
import com.example.debttracker.models.Options
import com.example.debttracker.reactivateRecord

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ClearDebt(
    debtor: Debtor?,
    navController: NavController
) {
    var deleteRecordDialog by remember {  mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Column (
                    modifier = Modifier.fillMaxWidth(),
                ){

                    Text("Update Debt Status")
                    Text("${debtor?.name}", style = MaterialTheme.typography.bodyMedium)
                }
            }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            if (debtor != null) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(6.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        // 🔹 Date
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Date recorded: ${debtor.debt?.dateAdded}, ${debtor.debt?.timeAdded}",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Options(
                                setInactive = { deactivateRecord(debtor) },
                                setActive = { reactivateRecord(debtor) },
                                onDelete = {
                                    deleteRecordDialog = true
                                           },
                                debtor = debtor
                            )
                        }

                        // 🔹 Debts
                        val debtMap = debtor.debt?.itemsOwed
                        debtMap?.forEach { (type, amount) ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                val label = when (type) {
                                    DebtType.Cash.name -> "Cash owed"
                                    DebtType.Bottle.name -> "Bottles owed"
                                    DebtType.Empty.name -> "Empties balance"
                                    DebtType.Fulls.name -> "Fulls owed"
                                    DebtType.Full_Bottle.name -> "Full bottles owed"
                                    DebtType.Plastic.name -> "Plastics owed"
                                    else -> "Other"
                                }

                                val displayAmount =
                                    if (type == DebtType.Cash.name) moneyFormat(amount)
                                    else amount.toString()

                                Text(
                                    text = label,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Text(
                                    text = if(type == DebtType.Cash.name) {
                                        moneyFormat(amount)
                                    }else{
                                        halfAndQuarter( displayAmount.toDoubleOrNull()?:0.0 )
                                    },
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                    }
                }
            } else {
                Text(
                    text = "No debt record found.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.error
                )
            }
            if (deleteRecordDialog) {
                debtor?.let {
                    CustomAlertDialog(it, navController = navController) {deleteRecordDialog = false}
                }
//                BasicAlertDialog(
//                    onDismissRequest = {}
//                ) {
//                    Surface(
//                        shape = MaterialTheme.shapes.large,
//                        tonalElevation = AlertDialogDefaults.TonalElevation,
//                    ){
//                        Column (
//                            modifier = Modifier.padding(24.dp)
//                        ){
//                            Text(
//                                text = "Delete debt record?",
//                                style = MaterialTheme.typography.titleLarge
//                            )
//                            Text(
//                                text = "Are you sure you want to delete this debt record?",
//                                style = MaterialTheme.typography.bodyMedium
//                            )
//                            Spacer(modifier = Modifier.height(24.dp))
//
//                            Row (
//                                horizontalArrangement = Arrangement.End,
//                                modifier = Modifier.fillMaxWidth()
//                            ) {
//                                TextButton(onClick = {deleteRecordDialog = false}) {
//                                    Text(text = "Cancel")
//
//                                }
//                                TextButton(onClick = {
//                                    if (debtor!= null){
//                                        removeDebtFromDB(debtor)
//                                    }
//                                    deleteRecordDialog = false}) {
//                                    Text(text = "Continue")
//                                }
//                            }
//                        }
//                    }
//                }
            }
        }
    }
}

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