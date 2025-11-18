package com.example.debttracker.uidesigns

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.example.debttracker.formatters.TimeAndDate
import com.example.debttracker.formatters.halfAndQuarter
import com.example.debttracker.formatters.moneyFormat
import com.example.debttracker.models.Debt
import com.example.debttracker.models.DebtType
import com.example.debttracker.models.Debtor
import com.example.debttracker.models.SharedViewModel
import java.util.Locale


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DebtCard(
    vm: SharedViewModel,
    debtor: Debtor?,
    navC: NavController
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp),
        shape = CardDefaults.shape,
        elevation = CardDefaults.cardElevation(6.dp),
        onClick = {
            vm.selectedRecord = debtor
            navC.navigate("ClearDebt") {
                popUpTo(navC.graph.findStartDestination().id) { saveState = true }
                launchSingleTop = true
                restoreState = true
            }
        },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                // 🔹 Debtor Name
                Text(
                    text = debtor?.name?:"noname",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
                // Optional visual indicator for low stock
                val active = (debtor?.debt?.active == true)
                val indicatorColor = if (!active)
                    MaterialTheme.colorScheme.error
                else
                    MaterialTheme.colorScheme.primary

                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .background(indicatorColor, CircleShape)
                )
            }

            // 🔹 Debts List
            val debts = debtor?.debt?.itemsOwed?.keys
            val debtTypes = DebtType.entries.toList()
            debts?.forEach { type ->
                val debtType = debtTypes.first { it.name.lowercase() == type.lowercase() }
                val amountOwed = debtor.debt.itemsOwed?.let { it[debtType.name]}

                val displayAmount =
                    if (debtType == DebtType.Cash) moneyFormat(amountOwed) else halfAndQuarter(amountOwed?:0.0)

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    val dtText = debtType.name.replaceFirstChar { if (it. isLowerCase()) it. titlecase(
                        Locale. getDefault()) else it. toString() }
                        Text(

                        text = "$dtText owed",
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

            // 🔹 Footer with Date
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(
                    onClick = {}
                ) {
                    
                }

                Text(
                    text = "${debtor?.debt?.dateAdded}, ${debtor?.debt?.timeAdded}",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.outline
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun ShowDebtCard(){
    DebtCard(
        vm = viewModel(),
        debtor = Debtor(
            id = "id",
            timeStamp = 456L,
            name = "chuks",
            debt = Debt(
                timeAdded = TimeAndDate().GetTimeString(),
                dateAdded = TimeAndDate().GetDateString(),
                lastEdited = "",
                itemsOwed = mutableMapOf(
                    DebtType.Cash.name to 6300.0
                )

            )
        ),
        navC = rememberNavController()
    )
}
