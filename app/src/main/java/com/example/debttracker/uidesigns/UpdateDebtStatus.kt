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
import com.example.debttracker.formatters.TimeAndDate
import com.example.debttracker.formatters.halfAndQuarter
import com.example.debttracker.formatters.moneyFormat
import com.example.debttracker.models.BottleType
import com.example.debttracker.models.CustomAlertDialog
import com.example.debttracker.models.Debt
import com.example.debttracker.models.DebtType
import com.example.debttracker.models.DebtRecord
import com.example.debttracker.models.Options
import com.example.debttracker.models.DialogKey
import com.example.debttracker.repositories.RecordsRepository


@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DebtDetailsScreen(
    debtRecord: DebtRecord?,
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
                            debtRecord?.let {
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
                            setInactive = {RecordsRepository.deactivateRecord(debtRecord?:DebtRecord()) },
                            setActive = { RecordsRepository.reactivateRecord(debtRecord?:DebtRecord()) },
                            onDelete = {
                                deleteEntireRecordDialog = true
                            },
                            debtRecord = debtRecord?:DebtRecord()
                        )
                        if (deleteEntireRecordDialog) {
                            debtRecord?.let {
                                CustomAlertDialog(
                                    debtRecord=it,
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
            if (debtRecord == null || debtRecord.debt?.itemsOwed.isNullOrEmpty()) {
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

            debtRecord.debt?.itemsOwed?.forEach { (type, amount) ->

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

                        Text(
                            text = type,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = amount,
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
                        )

//                        Column(
//                            verticalArrangement = Arrangement.spacedBy(4.dp)
//                        ) {
//
//                        }

                        // Actions
                        Options(
                            setInactive = { RecordsRepository.deactivateRecord(debtRecord) },
                            setActive = { RecordsRepository.reactivateRecord(debtRecord) },
                            onDelete = {
                                deleteRecordItemDialog = true
                                debtToDelete = type
                                       },
                            debtRecord = debtRecord
                        )
                    }
                }
            }
        }

        // Delete Confirmation Dialog

        if (deleteRecordItemDialog) {
            debtRecord?.let {
                CustomAlertDialog(
                    debtRecord=it,
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

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun ClearDebtDemo(){
    val new = Debt(
        timeAdded = TimeAndDate().GetTimeString(),
        dateAdded = TimeAndDate().GetDateString(),
        itemsOwed = mutableMapOf("Cash" to "6300")
    )
    val debtRecord = DebtRecord(name = "Mama Ejima", debt = new)
    DebtDetailsScreen(debtRecord = debtRecord, navController = rememberNavController())
}
