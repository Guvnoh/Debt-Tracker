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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.debttracker.formatters.moneyFormat
import com.example.debttracker.models.DebtClass
import com.example.debttracker.models.DebtType

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ClearDebt(debtor: Debtor?) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Update Debt Status") }
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
                        Text(
                            text = "Date recorded: ${debtor.debt?.dateAdded}, ${debtor.debt?.timeAdded}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        // 🔹 Debts
                        val debtMap = debtor.debt?.itemsOwed
                        debtMap?.forEach { (type, amount) ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                val label = when (type) {
                                    DebtType.MONEY.name -> "Cash owed"
                                    DebtType.EMPTY_BOTTLE.name -> "Bottles owed"
                                    DebtType.EMPTY_CRATE.name -> "Empties balance"
                                    DebtType.FULLS.name -> "Fulls owed"
                                    DebtType.FULL_BOTTLE.name -> "Full bottles owed"
                                    DebtType.PLASTIC.name -> "Plastics owed"
                                    else -> "Other"
                                }

                                val displayAmount =
                                    if (type == DebtType.MONEY.name) moneyFormat(amount)
                                    else amount.toString()

                                Text(
                                    text = label,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Text(
                                    text = displayAmount,
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
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun ClearDebtDemo(){
    val new = DebtClass()
    new.addDebt(DebtType.MONEY, 8500.0)
    new.addDebt(DebtType.MONEY, 8500.0)
    new.payDebt(DebtType.MONEY, 10000.0)
    new.addDebt(DebtType.EMPTY_BOTTLE, 6.0)
    ClearDebt(debtor = Debtor("chuks", debt = new))
}