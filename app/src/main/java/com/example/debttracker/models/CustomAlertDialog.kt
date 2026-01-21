package com.example.debttracker.models

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.debttracker.Screen
import com.example.debttracker.repositories.RecordsRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAlertDialog(
    debtRecord: DebtRecord,
    debt: String,
    navController: NavController,
    caseKey: DialogKey,
    dismissDialog: () -> Unit,


    ){
    //var deleteRecordDialog by remember {  mutableStateOf(false) }
    BasicAlertDialog(
        onDismissRequest = {}
    ) {
        Surface(
            shape = MaterialTheme.shapes.large,
            tonalElevation = AlertDialogDefaults.TonalElevation,
        ){
            Column (
                modifier = Modifier.padding(24.dp)
            ){
                Text(
                    text = "Delete debt record?",
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = "Are you sure you want to delete this debt record?",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(24.dp))

                Row (
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextButton(onClick = {dismissDialog()}) {
                        Text(text = "Cancel")

                    }
                    TextButton(onClick = {
                        when (caseKey ){
                            DialogKey.debtCard -> {
                                RecordsRepository.removeSingleDebt(debtRecord = debtRecord, debt = debt)
                                navController.popBackStack()
//                                navController.navigate(Screen.UpdateDebtScreen.route)
                                navController.navigate(Screen.UpdateDebtScreen.route) {
                                    popUpTo(navController.graph.findStartDestination().id){
                                        inclusive = true
                                    }
                                    launchSingleTop = true
                                }
                            }
                            DialogKey.topAppBar -> {
                                RecordsRepository.removeDebtFromDB(key=debtRecord.id.toString())
                                navController.popBackStack()
                            }
                        }
                        dismissDialog()

                    }) {
                        Text(text = "Continue")
                    }
                }
            }
        }
    }
}

enum class DialogKey{
    topAppBar,
    debtCard
}