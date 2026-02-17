package com.example.debttracker.models

import android.content.Context
import android.widget.Toast
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
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.debttracker.ui.theme.InactiveRed

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteCardsDialog(
    onDelete: (List<DebtRecord>) -> Unit,
    records: List<DebtRecord>,
    alert: MutableState<Boolean>,
    context: Context
) {
    if (alert.value) {
        BasicAlertDialog(
            onDismissRequest = { alert.value = false },
        ) {
            Surface(
                shape = MaterialTheme.shapes.large,
                tonalElevation = AlertDialogDefaults.TonalElevation,
            ) {
                Column(
                    modifier = Modifier.padding(24.dp)
                ) {
                    Text(
                        text = "Delete Record(s)?",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = "Are you sure you want to delete selected record(s)?",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(24.dp))

                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        TextButton(onClick = { alert.value = false }) {
                            Text(text = "Cancel")
                        }
                        TextButton(onClick = {
                            onDelete(records)
                            alert.value = false
                            Toast.makeText(context, "Debt record Deleted", Toast.LENGTH_SHORT).show()
                        }) {
                            Text(
                                text = "Continue",
                                color = InactiveRed
                            )
                        }
                    }
                }
            }
        }
    }
}
